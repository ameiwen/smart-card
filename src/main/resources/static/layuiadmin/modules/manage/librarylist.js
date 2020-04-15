layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    let prefix = "/manage/library";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        let table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        let libraryTab = table.render({
            elem: '#libraryTab',
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
                    {field: 'leader', title: '领导人', align: 'center'},
                    {field: 'position', title: '所在位置', align: 'center'},
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
            libraryTab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        //监听工具条(删除)
        table.on('tool(libraryTab)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'remove') {
                remove(obj)
            }else if(layEvent === 'edit'){
                edit(obj)
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#libraryBtn", function () {
            add();
        })

        let add = function(obj){
            layer.open({
                type: 2,
                title: '添加图书馆信息',
                content: prefix + '/add',
                area: ['480px', '350px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#library-add-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(library-add-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/save",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    libraryTab.reload();
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
        };

        let edit = function(obj){
            layer.open({
                type: 2,
                title: '修改图书馆信息',
                content: prefix + '/edit/' + obj.data.id,
                area: ['480px', '350px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    let iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#library-edit-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(library-edit-submit)', function(data){
                        let field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/update",
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    libraryTab.reload();
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

    });

    exports('librarylist', {})
});