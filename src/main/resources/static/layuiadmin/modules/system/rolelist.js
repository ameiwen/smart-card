layui.define(function(exports) {

    /*
      下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
    */

    var prefix = "/system/role";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        var table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        var roleTab = table.render({
            elem: '#roleTable',
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
                {field: 'id', title: '序号'},
                {field: 'name', title: '角色名', align: 'center'},
                {field: 'remark', title: '角色描述', align: 'center'},
                {
                    field: 'status', title: '状态', align: 'center', templet: function (d) {
                        if (d.status === '1') {
                            return '<span class="layui-badge layui-bg-green">正常</span>';
                        } else if (d.status === '0') {
                            return '<span class="layui-badge layui-bg-red">禁用</span>';
                        }
                    }
                },
                {templet: '#tool', title: '操作', align: 'center'}
            ]]
        });

        //搜索
        form.on('submit(search)', function (data) {
            roleTab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        //监听工具条(删除、编辑、重置密码)
        table.on('tool(roleTable)', function (obj) {
            var layEvent = obj.event;
            if (layEvent === 'remove') {
                remove(obj)
            } else if (layEvent === 'edit') {
                edit(obj.data.id)
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#roleBtn", function () {
            add();
        })

        //添加
        var add = function () {
            var index = layui.layer.open({
                title: "新建角色信息",
                type: 2,
                content: prefix + "/add/",
                success: function (layero, index) {
                    //打开的页面自适应高度
                    layer.iframeAuto(index);
                    //提示定时器
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回页面展示列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 300);
                },
                end: function () {
                    roleTab.reload();
                }
            });
            layui.layer.full(index);
        };

        //修改
        var edit = function (id) {
            var index = layui.layer.open({
                title: "编辑角色信息",
                type: 2,
                content: prefix + "/edit/" + id,
                success: function (layero, index) {
                    //提示定时器
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回页面展示列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 300);
                },
                end: function () {
                    //关闭编辑弹窗后刷新页面
                    roleTab.reload();
                }
            });
            layui.layer.full(index);
        };


        //删除角色
        var remove = function (obj) {
            layer.confirm('确定删除此角色？', function (index) {
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

    });

    exports('rolelist', {})
});