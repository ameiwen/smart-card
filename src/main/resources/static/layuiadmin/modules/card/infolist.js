layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    let prefix = "/card/info";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        let table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        let infoTab = table.render({
            elem: '#infoTab',
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
                {field: 'id', title: '卡号', align: 'center'},
                {field: 'userId', title: '学号/教师编号', align: 'center'},
                {field: 'userName', title: '姓名', align: 'center'},
                {field: 'borrowNum', title: '借书额度', align: 'center'},
                {
                    title: '余额', align: 'center',templet: function (d) {
                        return '￥'+d.assetAmount;
                    }
                },
                {
                    title: '状态', align: 'center',templet: function (d) {
                        if(d.status == 1){
                            return "<span class='layui-badge layui-bg-green'>正常</span>"
                        }
                        if(d.status == 0){
                            return "<span class='layui-badge'>已挂失</span>"
                        }
                    }
                },
                {field: 'createTime', title: '创建时间',templet:'<div>{{layui.util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}</div>'},
                {templet: '#tool', title: '操作',width:150, align: 'center'}
            ]]
        });

        //搜索
        form.on('submit(search)', function (data) {
            infoTab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

        //监听工具条(删除)
        table.on('tool(infoTab)', function (obj) {
            let layEvent = obj.event;
            if (layEvent === 'lost') {
                lost(obj);
            }else if(layEvent === 'relost'){
                lost(obj);
            }
        });

        //删除用户
        let lost = function (obj) {
            layer.confirm('确定要挂失这张卡吗？', function (index) {
                $.ajax({
                    url: prefix + '/lost',
                    method: "POST",
                    data: {"id": obj.data.id},
                    success: function (data) {
                        if (data.code == 0) {
                            infoTab.reload();
                        } else {
                            layer.msg(data.msg, {"icon": 2});
                        }
                    }
                });
                layer.close(index);
            });
        };

    });

    exports('infolist', {})
});