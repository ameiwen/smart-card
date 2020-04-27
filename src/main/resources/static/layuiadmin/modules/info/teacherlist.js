layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    let prefix = "/info/teacher";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        let table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        let teacherTab = table.render({
            elem: '#teacherTab',
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
                    {field: 'name', title: '姓名', align: 'center'},
                    {field: 'sex', title: '性别', align: 'center'},
                    {
                        title: '状态', align: 'center',templet: function (d) {
                            if(d.status == 1){
                                return "<span class='layui-badge layui-bg-green'>正常</span>"
                            }
                            if(d.status == 0){
                                return "<del class='layui-badge'>已删除</del>"
                            }
                        }
                    },
                    {field: 'createTime', title: '创建时间',templet:'<div>{{layui.util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}</div>'},
                    {templet: '#tool', title: '操作',width:150, align: 'center'}
            ]]
        });

        //搜索
        form.on('submit(search)', function (data) {
            teacherTab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        //监听工具条(删除)
        table.on('tool(teacherTab)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'remove') {
                remove(obj)
            }else if(layEvent === 'edit'){
                edit(obj)
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#teacherBtn", function () {
            add();
        })

        let add = function(obj){
            layer.open({
                type: 2,
                title: '添加教师信息',
                content: prefix + '/add',
                area: ['480px', '620px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#teacher-add-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(teacher-add-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/save",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    teacherTab.reload();
                                    layer.msg(data.msg, {"icon": 1});
                                    layer.close(index); //关闭弹层
                                } else {
                                    layer.msg(data.msg, {"icon": 2});
                                }
                            }
                        });
                    });
                    submit.trigger('click');
                }
                ,success: function(layero, index){
                }
            })
        }

        let edit = function(obj){
            layer.open({
                type: 2,
                title: '修改教师信息',
                content: prefix + '/edit/' + obj.data.id,
                area: ['480px', '620px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    let iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#teacher-edit-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(teacher-edit-submit)', function(data){
                        let field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/update",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    teacherTab.reload();
                                    layer.msg(data.msg, {"icon": 1});
                                    layer.close(index); //关闭弹层
                                } else {
                                    layer.msg(data.msg, {"icon": 2});
                                }
                            }
                        });
                    });
                    submit.trigger('click');
                }
                ,success: function(layero, index){
                }
            })
        }

        //删除用户
        let remove = function (obj) {
            layer.confirm('确定删除此教师？', function (index) {
                $.ajax({
                    url: prefix + '/remove',
                    method: "POST",
                    data: {"id": obj.data.id},
                    success: function (data) {
                        if (data.code == 0) {
                            teacherTab.reload();
                        } else {
                            layer.msg(data.msg, {"icon": 2});
                        }
                    }
                });
                layer.close(index);
            });
        };

    });

    exports('teacherlist', {})
});