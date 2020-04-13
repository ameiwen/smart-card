<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.springframework.org/schema/data/jaxb">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" th:href="@{/layuiadmin/layui/css/layui.css}" media="all">
    <link rel="stylesheet" th:href="@{/layuiadmin/style/admin.css}" media="all">
</head>
<body>
<div class="layui-form" lay-filter="${classname}-add-form" id="${classname}-add-form" style="padding: 20px 30px 0 0;">
    <#list columns as item>
    <#if item.attrname != pk.attrname>
    <div class="layui-form-item">
        <label class="layui-form-label">${item.comments}</label>
        <div class="layui-input-block">
            <input class="layui-input" type="text" name="${item.attrname}" lay-verify="required" autocomplete="off"/>
        </div>
    </div>
    </#if>
    </#list>
    <div class="layui-form-item layui-hide">
        <button class="layui-btn" lay-submit lay-filter="${classname}-add-submit" id="${classname}-add-submit">提交</button>
    </div>
</div>
<script th:src="@{/layuiadmin/layui/layui.js}"></script>
<script>
    layui.config({
        base: '/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form'], function(){
        var $ = layui.$
            ,form = layui.form ;
    })
</script>
</body>
</html>