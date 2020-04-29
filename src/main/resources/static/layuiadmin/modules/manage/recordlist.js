layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */

    let prefix = "/manage/record";

    //区块轮播切换
    layui.use(['admin', 'carousel'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,carousel = layui.carousel
            ,element = layui.element
            ,device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function(){
            var othis = $(this);
            carousel.render({
                elem: this
                ,width: '100%'
                ,arrow: 'none'
                ,interval: othis.data('interval')
                ,autoplay: othis.data('autoplay') === true
                ,trigger: (device.ios || device.android) ? 'click' : 'hover'
                ,anim: othis.data('anim')
            });
        });

        element.render('progress');

    });

    //数据概览
    layui.use(['admin', 'carousel', 'echarts'], function(){
        var $ = layui.$
            ,admin = layui.admin
            ,carousel = layui.carousel
            ,echarts = layui.echarts;


        var echartsApp = [], options = [
            //访客浏览器分布
            {
                title : {
                    text: '书籍喜爱分析图(只统计借书情况)',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data: [],
                },
                series : [{
                    name:'书籍类型',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[]
                }]
            },
        ]
            ,elemDataView = $('#LAY-index-dataview').children('div')
            ,renderDataView = function(index){
            echartsApp[index] = echarts.init(elemDataView[index], layui.echartsTheme);
            echartsApp[index].setOption(options[index]);
            //window.onresize = echartsApp[index].resize;
            admin.resize(function(){
                echartsApp[index].resize();
            });
        };


        //没找到DOM，终止执行
        if(!elemDataView[0]) return;

        $.ajax({
            url : prefix + "/report",
            method:'POST',
            success:function (res) {
                if(res.code == 0){
                    options[0].legend.data = res.headers;
                    options[0].series[0].data = res.data;
                    renderDataView(0);
                }
            }
        });


        //监听数据概览轮播
        var carouselIndex = 0;
        carousel.on('change(LAY-index-dataview)', function(obj){
            renderDataView(carouselIndex = obj.index);
        });

    });


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
               {field: 'id', title: '编号', align: 'center'},
               {field: 'cardId', title: '卡片ID', align: 'center'},
               {field: 'bookId', title: '书籍ID', align: 'center'},
               {
                       title: '类型', align: 'center',templet: function (d) {
                           if(d.eventType == 1){
                               return "<span class='layui-badge'>借书</span>"
                           }
                           if(d.eventType == 2){
                               return "<span class='layui-badge'>还书</span>"
                           }
                       }
               },
               {field: 'opUser', title: '操作人', align: 'center'},
               {field: 'createTime', title: '创建时间',templet:'<div>{{layui.util.toDateString(d.createTime,"yyyy-MM-dd HH:mm:ss")}}</div>'},
               {templet: '#tool', title: '操作',width:150, align: 'center'}
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

        //监听工具条(删除)
        table.on('tool(recordTab)', function (obj) {
            var layEvent = obj.event;
            if(layEvent === 'edit'){
                edit(obj)
            }
        });

        //新增顶级菜单点击
        $("body").on("click", "#borrowBook", function () {
            borrowReturnBook(1);
        });
        $("body").on("click", "#returnBook", function () {
            borrowReturnBook(2);
        });


        let borrowReturnBook = function(type){
            let str = "借书";
            if(type == 2){
                str = "还书";
            }
            layer.open({
                type: 2,
                title: str,
                content: prefix + '/borrow',
                area: ['600px', '500px'],
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var iframeWindow = window['layui-layer-iframe'+ index]
                        ,submit = layero.find('iframe').contents().find("#record-add-submit");
                    //监听提交
                    iframeWindow.layui.form.on('submit(record-add-submit)', function(data){
                        var field = data.field; //获取提交的字段
                        //提交 Ajax 成功后，静态更新表格中的数据
                        $.ajax({
                            url: prefix + "/save/" + type,
                            data: field,
                            method: "POST",
                            success: function (data) {
                                if (data.code == 0) {
                                    recordTab.reload();
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

    });

    exports('recordlist', {})
});