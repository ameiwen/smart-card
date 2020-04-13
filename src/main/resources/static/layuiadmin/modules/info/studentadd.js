layui.define(function(exports) {

    /*
     * generator code
     *  下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
     */
    let prefix = "/info/student";

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

        //二级目录加载
        form.on('select(facultyId)', function(obj){
            //获取数据
            $.ajax({
                url: prefix + "/addStudentSpecialtyInit/" + obj.value,
                method:'POST',
                success:function (data) {
                    if(data.code === 0){
                        $("#specialtyId").html("<option value=''>请选择专业</option>");
                        $.each(data.data,function(index,item){
                            $('#specialtyId').append(new Option(item.name,item.id));//往下拉菜单里添加元素
                            form.render('select'); //这个很重要
                        });
                        //三级目录加载为空
                        $("#classId").html("<option value=''>请选择班级</option>");
                        form.render('select'); //这个很重要
                    }
                }
            });
        });

        //三级目录加载
        form.on('select(specialtyId)', function(obj){
            $.ajax({
                url: prefix + "/addStudentClassesInit",
                method:'POST',
                data:{
                    "specialtyId":obj.value,
                    "facultyId":$("#facultyId").find("option:selected").val()
                },
                success:function (data) {
                    if(data.code === 0){
                        $("#classId").html("<option value=''>请选择班级</option>");
                        $.each(data.data,function(index,item){
                            $('#classId').append(new Option(item.name,item.id));//往下拉菜单里添加元素
                            form.render('select'); //这个很重要
                        });
                    }
                }
            });

        });

    });

    exports('groupadd', {})
});