layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    let prefix = "/card/record";

    //渲染表格
    layui.use(['table', 'layer', 'jquery', 'form'], function () {
        let table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;

        //渲染表格
        let recordTab = table.render({
            elem: '#recordTab',
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
                {field: 'cardId', title: '卡号', align: 'center'},
                {field: 'eventId', title: '事件ID', align: 'center'},
                {
                    title: '事件名称', align: 'center',templet: function (d) {
                        if(d.event == '3'){
                            return "充值";
                        }else if(d.event == '2'){
                            return "超市消费";
                        }else{
                            return "食堂消费";
                        }
                    }
                },
                {
                    title: '金额', align: 'center',templet: function (d) {
                        if(d.event == '3'){
                            return '+￥'+d.payAmt;
                        }else{
                            return '-￥'+d.payAmt;
                        }
                    }
                },
                {
                    title: '状态', align: 'center',templet: function (d) {
                        if(d.status == 1){
                            return "<span class='layui-badge layui-bg-green'>正常</span>"
                        }
                    }
                },
                {field: 'createTime', title: '创建时间',templet:'<div>{{layui.util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}</div>'},
            ]]
        });

        //搜索
        form.on('submit(search)', function (data) {
            recordTab.reload({
                where: data.field,//设置查询参数
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
            return false;
        });

    });

    exports('recordlist', {})
});