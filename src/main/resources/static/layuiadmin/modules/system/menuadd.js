layui.define(function(exports){

  /*
    下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
  */

  var prefix = "/system/menu";

  layui.use(['form', 'layer','jquery'], function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    //表单验证
    form.verify({
      name : function(value){
        if(value.length <= 1){
          return '请输入正确的菜单名';
        }
      },
      orderNum : [
        /^[0-9]{1,3}$/
        ,'排序号只能小于1000的数字'
      ],
    });

    //表单提交
    form.on('submit(save)', function(data){
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

    $("#ico-btn").click(function(){
      layer.open({
        type: 2,
        title:'图标列表',
        content: '/layuiadmin/system/icons.html',
        area: ['380px', '60%'],
        success: function(layero, index){
          //var body = layer.getChildFrame('.ico-list', index);
          //console.log(layero, index);
        }
      });
      return false;
    });

  });
  //模块方法
  exports('menuadd', {})
});