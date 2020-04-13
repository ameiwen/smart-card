package ${basePackage}.system.controller;

import ${basePackage}.controller.base.BaseController;
import ${basePackage}.system.model.${className};
import ${basePackage}.system.service.${className}Service;
import ${basePackage}.utils.Result;
import com.github.pagehelper.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/${pathName}/${classname}")
public class ${className}Controller extends BaseController {

    @Autowired
    private ${className}Service ${classname}Service;

    @GetMapping
    @RequiresPermissions(value = "${pathName}:${classname}:${classname}")
    public String ${classname}() {
        return  "${pathName}/${classname}/${classname}";
    }

    /**
     * 数据列表
     * @param page
     * @param ${classname}
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("${pathName}:${classname}:${classname}")
    public Result list(Page<${className}> page, ${className} ${classname}) {
        return ${classname}Service.select${className}ByPage(page,${classname});
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(value = "/add")
    @RequiresPermissions("${pathName}:${classname}:add")
    public String add() {
        return "${pathName}/${classname}/add";
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{${pk.attrname}}")
    @RequiresPermissions("${pathName}:${classname}:edit")
    public String edit(@PathVariable("${pk.attrname}") ${pk.attrType} ${pk.attrname}, Model model) {
        ${className} ${classname} = ${classname}Service.select${className}ByID(${pk.attrname});
        model.addAttribute("${classname}", ${classname});
        return "${pathName}/${classname}/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("${pathName}:${classname}:add")
    public Result save${className}(${className} ${classname}) {
        return ${classname}Service.save${className}(${classname});
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("${pathName}:${classname}:edit")
    public Result update(${className} ${classname}) {
        return ${classname}Service.update${className}(${classname});
    }

    /**
     * 删除
     */
    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("${pathName}:${classname}:remove")
    public Result remove(${pk.attrType} ${pk.attrname}) {
        return ${classname}Service.remove${className}(${pk.attrname});
    }


}
