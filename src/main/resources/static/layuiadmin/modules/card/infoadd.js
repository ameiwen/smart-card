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

        $("#student").change(function () {
            let userId = $("#student").val();
            $.ajax({
                url:prefix + "/selectUserInfoById/" +userId + "/0",
                method:'POST',
                success:function (data) {
                    if(data.code == 0){
                        $("#studentUserName").val(data.data.name);
                        $("#faculty").val(data.data.faculty.name);
                        $("#specialty").val(data.data.specialty.name);
                        $("#studentError").text("");
                    }else{
                        $("#studentError").text(data.msg);
                    }
                }
            })
        })

        $("#teacher").change(function () {
            let userId = $("#teacher").val();
            $.ajax({
                url:prefix + "/selectUserInfoById/" +userId + "/1",
                method:'POST',
                success:function (data) {
                    if(data.code == 0){
                        $("#teacherUserName").val(data.data.name);
                        $("#teacherError").text("");
                    }else{
                        $("#teacherError").text(data.msg);
                    }
                }
            })
        })

        form.on('submit(saveStudent)', function (data) {
            $.ajax({
                url : prefix + "/save",
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
        form.on('submit(saveTeacher)', function (data) {
            $.ajax({
                url : prefix + "/save",
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

    exports('infoadd', {})
});