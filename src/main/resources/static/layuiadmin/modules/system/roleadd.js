layui.define(function(exports){

  /*
    下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
  */

  var prefix = "/system/role";

  var menuIds;

  layui.use(['form', 'layer','jquery'], function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    getMenuTreeData();

    //表单提交
    form.on('submit(save)', function(data){
      getAllSelectNodes();
      //设置参数
      data.field.menuIds = menuIds+'';
      $.ajax({
        url: prefix + "/save",
        data:data.field,
        method:"POST",
        success:function (data) {
          if(data.code == 0){
            //新增成功关闭此窗口
            var index = parent.layer.getFrameIndex(window.name); //获取当前窗口的name
            parent.layer.close(index);		//关闭窗口
          }else{
            layer.msg(data.msg,{"icon":2});
          }
        }
      });
      //防止提交表单刷新
      return false;
    });


    function getMenuTreeData() {
      $.ajax({
        type: "POST",
        url: "/system/menu/tree",
        success: function (menuTree) {
          loadMenuTree(menuTree);
        }
      });
    };

    function loadMenuTree(menuTree) {
      $('#menuTree').jstree({
        "plugins" : [ "wholerow", "checkbox","state" ],
        'core' : {
          'data' : menuTree
        },
        "checkbox" : {
          "three_state" : true,
        },
        "state":{
          "opened":true,
        }
      });
    }

    function getAllSelectNodes() {
      var ref = $('#menuTree').jstree(true); // 获得整个树
      menuIds = ref.get_selected(); // 获得所有选中节点的，返回值为数组
      $("#menuTree").find(".jstree-undetermined").each(function (i, element) {
        menuIds.push($(element).closest('.jstree-node').attr("id"));
      });
    };

  });


  //模块方法
  exports('roleadd', {})
});