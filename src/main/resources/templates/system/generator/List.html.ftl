<!DOCTYPE html>
<html xmlns:th="http://www.springframework.org/schema/data/jaxb" xmlns:shiro="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" th:href="@{/layuiadmin/layui/css/layui.css}" media="all">
  <link rel="stylesheet" th:href="@{/layuiadmin/style/admin.css}" media="all">
</head>
<body>
<div class="layui-fluid">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
      <div class="layui-card">
        <div class="layui-card-header">列表</div>
        <div class="layui-card-body">
          <blockquote class="layui-elem-quote">
            <form class="layui-form" action="">
              <div class="layui-inline">
                <div class="layui-input-inline">
                  <input type="text" name="${pk.attrname}" placeholder="请输入值" class="layui-input">
                </div>
                <!--lay-filter为绑定查询的标志   -->
                <a class="layui-btn" lay-submit="" lay-filter="search">查询</a>
              </div>
              <div class="layui-inline">
                <a id="${classname}Btn" class="layui-btn audit_btn layui-btn-normal">添加</a>
              </div>
            </form>
          </blockquote>
          <table class="layui-table" lay-size="lg" lay-filter="${classname}Table" id="${classname}Table"></table>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/html" id="tool">
  <a class="layui-btn layui-btn-sm" lay-event="edit" title="编辑">
    <i class="layui-icon layui-icon-edit"></i>
  </a>
  <a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="remove" title="删除">
    <i class="layui-icon layui-icon-delete"></i>
  </a>
</script>
<script th:src="@{/layuiadmin/layui/layui.js}"></script>
<script>
  layui.config({
    base: '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index','${pathName}/${classname}list']);
</script>
</body>
</html>