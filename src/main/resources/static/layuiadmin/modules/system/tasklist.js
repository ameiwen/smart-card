layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    var prefix = "/system/task";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        var taskTab = table.render({
            elem: '#taskTable',
            url: prefix + '/list',
            page: true, //开启分页
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            },
            request: {  //重构分页请求的参数
                pageName: 'pageNum', //页码的参数名称，默认：page
                limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            cols: [[
                {field: 'id', title: '编号', align: 'center'},
                {field: 'jobName', title: '任务名称', align: 'center'},
                {field: 'jobGroup', title: '任务分组', align: 'center'},
                {field: 'beanClass', title: '任务类', align: 'center'},
                {field: 'cronExpression', title: 'cron表达式', align: 'center'},
                {
                   field: 'jobStatus', title: '任务状态', align: 'center', templet: function (d) {
                       if (d.jobStatus === '1') {
                           return '<span class="layui-badge layui-bg-green">正常</span>';
                       } else if (d.jobStatus === '0') {
                           return '<span class="layui-badge layui-bg-red">禁用</span>';
                       }
                   }
                },
                {templet: '#tool', title: '操作', align: 'center',width:200},
            ]]
        });

        //搜索
        form.on('submit(search)', function (data) {
            taskTab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        //监听工具条(删除、编辑)
        table.on('tool(taskTable)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'remove') {
                remove(obj)
            } else if (layEvent === 'edit') {
                edit(obj.data.id)
            } else if(layEvent === 'change'){
                changeJobStatus(obj.data.id,obj.data.jobStatus);
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#taskBtn", function () {
            add();
        })

        //添加
        var add = function () {
            layer.open({
                type: 2,
                title: '添加任务',
                content: prefix + '/add',
                area: ['480px', '500px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#task-add-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(task-add-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/save",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    taskTab.reload();
                                    layer.msg(data.msg, {"icon": 1});
                                } else {
                                    layer.msg(data.msg, {"icon": 2});
                                }
                            }
                        });
                        layer.close(index); //关闭弹层
                    });
                    submit.trigger('click');
                }
                ,success: function(layero, index){
                }
            })
        }

        //修改
        var edit = function (id) {
            layer.open({
                type: 2,
                title: '修改任务',
                content: prefix + '/edit/' + id,
                area: ['480px', '500px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#task-edit-submit");

                    //监听提交
                    iframeWindow.layui.form.on('submit(task-edit-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        console.log(field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/update",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    taskTab.reload();
                                    layer.msg(data.msg, {"icon": 1});
                                } else {
                                    layer.msg(data.msg, {"icon": 2});
                                }
                            }
                        });
                        layer.close(index); //关闭弹层
                    });
                    submit.trigger('click');
                }
                ,success: function(layero, index){
                }
            })
        }

        //删除用户
        var remove = function (obj) {
            layer.confirm('确定删除此任务？', function (index) {
                $.ajax({
                    url: prefix + '/remove',
                    method: "POST",
                    data: {"id": obj.data.id},
                    success: function (data) {
                        if (data.code == 0) {
                            obj.del();
                        } else {
                            layer.msg(data.msg, {"icon": 2});
                        }
                    }
                });
                layer.close(index);
            });
        };

        //修改任务状态
        var changeJobStatus = function (id,status) {
            var label = "停止";
            var cmd = "stop";
            if(status === '0'){
                label = "开启";
                cmd = "start";
            }
            layer.confirm('确定'+ label + '此任务？', function (index) {
                $.ajax({
                    url: prefix + '/changeJobStatus',
                    method: "POST",
                    data: {
                        "id": id,
                        "cmd": cmd,
                    },
                    success: function (data) {
                        if (data.code == 0) {
                            taskTab.reload();
                            layer.msg(data.msg, {"icon": 1});
                        } else {
                            layer.msg(data.msg, {"icon": 2});
                        }
                    }
                });
                layer.close(index);
            });
        }

    });

    exports('tasklist', {})
});