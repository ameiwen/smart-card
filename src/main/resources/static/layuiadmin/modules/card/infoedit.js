layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */
    let prefix = "/card/info";

    layui.use(['table', 'layer', 'jquery','form','laydate','transfer','util'], function () {
        let table = layui.table,
            layer = layui.layer,
            $ = layui.jquery,
            laydate = layui.laydate,
            form = layui.form,
            transfer = layui.transfer,
            util = layui.util;

        //表单自定义验证
        form.verify({

        });

        $("#id").change(function () {
            let userId = $("#id").val();
            $.ajax({
                url:prefix + "/selectCardInfoById/" +userId,
                method:'POST',
                success:function (data) {
                    if(data.code == 0){
                        $("#assetAmount").val(data.data.assetAmount);
                        $("#userName").val(data.data.userName);
                        $("#error").text("");
                    }else{
                        $("#error").text(data.msg);
                    }
                }
            })
        })


        form.on('submit(updateCard)', function (data) {
            $.ajax({
                url : prefix + "/update",
                method: 'POST',
                data:data.field,
                success:function (res) {
                    if(res.code == 0){
                        layer.msg(res.msg, {"icon": 1});
                    }else {
                        layer.msg(res.msg, {"icon": 2});
                    }
                }
            })
        });

    });

    exports('infoedit', {})
});