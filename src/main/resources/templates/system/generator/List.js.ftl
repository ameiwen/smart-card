layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    var prefix = "/${pathName}/${classname}";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        var ${classname}Tab = table.render({
            elem: '#${classname}Table',
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
                <#list columns as item>
                    {field: '${item.attrname}', title: '${item.comments}', align: 'center'},
                </#list>
                //{
                //    field: 'status', title: '状态', align: 'center', templet: function (d) {
                //        if (d.status === 1) {
                //            return '<span class="layui-badge layui-bg-green">正常</span>';
                //        } else if (d.status === 0) {
                //            return '<span class="layui-badge layui-bg-red">禁用</span>';
                //        }
                //    }
                //},
                {templet: '#tool', title: '操作', align: 'center'}
            ]]
        });

        //搜索
        form.on('submit(search)', function (data) {
            ${classname}Tab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        //监听工具条(删除、编辑、重置密码)
        table.on('tool(${classname}Table)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'remove') {
                remove(obj)
            } else if (layEvent === 'edit') {
                edit(obj.data.id)
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#${classname}Btn", function () {
            add();
        })

        //添加
        var add = function () {
            layer.open({
                type: 2,
                title: '添加用户',
                content: prefix + '/add',
                area: ['480px', '500px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#${classname}-add-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(${classname}-add-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/save",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    ${classname}Tab.reload();
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
                title: '修改用户',
                content: prefix + '/edit/' + ${pk.attrname},
                area: ['480px', '500px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#${classname}-edit-submit");

                    //监听提交
                    iframeWindow.layui.form.on('submit(${classname}-edit-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        console.log(field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/update",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    ${classname}Tab.reload();
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
            layer.confirm('确定删除此菜单？', function (index) {
                $.ajax({
                    url: prefix + '/remove',
                    method: "POST",
                    data: {"id": obj.data.${pk.attrname}},
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

    });

    exports('${classname}list', {})
});