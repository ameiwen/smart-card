layui.define(function(exports) {

    /*
      下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
    */

    var prefix = "/system/generator";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        var genTab = table.render({
            elem: '#genTable',
            url: prefix + '/list',
            cols: [[
                {field: 'tableName', title: '表名称'},
                {field: 'engine', title: 'engine', align: 'center'},
                {field: 'tableComment', title: '表描述', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {templet: '#tool', title: '操作', align: 'center'}
            ]]
        });

        //监听工具条(删除、编辑、重置密码)
        table.on('tool(genTable)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'edit') {
                location.href = prefix + "/code/" + obj.data.tableName;
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#genBtn", function () {
            edit();
        });


        //修改
        var edit = function () {
            layer.open({
                type: 2,
                title: '修改策略',
                content: prefix + '/edit',
                area: ['480px', '500px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#gen-edit-submit");

                    //监听提交
                    iframeWindow.layui.form.on('submit(gen-edit-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        console.log(field);
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/update",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    genTab.reload();
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

    });

    exports('genlist', {})
});