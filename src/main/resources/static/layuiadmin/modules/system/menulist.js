layui.define(function(exports) {

  /*
    下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
  */

  var prefix = "/system/menu";

  layui.extend({
    treetable: 'treetable-lay/treetable'
  }).use(['treetable', 'jquery', 'table'], function () {

    //声明需要的模块
    var treetable = layui.treetable,
        $ = layui.jquery,
        table = layui.table;

    // 渲染树形表格
    treetable.render({
      treeColIndex: 1,          // 树形图标显示在第几列
      treeSpid: 0,             // 最上级的父级id
      treeIdName: 'id',       // id字段的名称
      treePidName: 'parentId',     // 对应父级菜单ID
      treeDefaultClose: false,   // 是否默认折叠
      treeLinkage: true,        // 父级展开时是否自动展开所有子级
      elem: '#menuTable',
      url: prefix + '/list',
      method: 'POST',
      cols: [[
        {type: 'numbers'},
        {field: 'name', title: '名称'},
        {
          field: 'icon', title: '图标', templet: function (d) {
            return '<i class="' + d.icon + '"></i>';
          }, align: 'center'
        },
        {
          field: 'type', title: '类型', templet: function (d) {
            if (d.type == 0) {
              return '<span class="layui-badge layui-bg-green">目录</span>'
            }
            if (d.type == 1) {
              return '<span class="layui-badge layui-bg-orange">菜单</span>'
            }
            if (d.type == 2) {
              return '<span class="layui-badge layui-bg-blud">按钮</span>'
            }
          }, align: 'center'
        },
        {field: 'url', title: '地址', align: 'center'},
        {field: 'perms', title: '权限标识', align: 'center'},
        {templet: '#tool', title: '操作', align: 'center'}
      ]]
    });

    //监听工具条(删除、编辑、添加)
    table.on('tool(menuTable)', function (obj) {
      var layEvent = obj.event;
      if (layEvent === 'remove') {
        remove(obj);
      } else if (layEvent === 'edit') {
        edit(obj.data.id);
      } else if (layEvent === "add") {
        add(obj.data.id);
      }
    });

    //删除菜单
    var remove = function (obj) {
      layer.confirm('确定删除此菜单？', function (index) {
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

    //打开编辑页
    var edit = function (id) {
      var index = layui.layer.open({
        title: "编辑菜单信息",
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
          location.reload();
        }
      });
      layui.layer.full(index);
    };

    //打开新增页
    var add = function (id) {
      var index = layui.layer.open({
        title: "新建菜单信息",
        type: 2,
        content: prefix + "/add/" + id,
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
          location.reload();
        }
      });
      layui.layer.full(index);
    };

    //新增顶级菜单点击
    $("body").on("click", "#topBtn", function () {
      add(0);
    })

  });

  exports('menulist', {})
});