package com.zx.card.system.controller;

import com.github.pagehelper.Page;
import com.zx.card.controller.base.BaseController;
import com.zx.card.system.model.Task;
import com.zx.card.system.service.TaskService;
import com.zx.card.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/system/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @RequiresPermissions(value = "system:task:task")
    public String task() {
        return  "system/task/task";
    }

    /**
     * 数据列表
     * @param page
     * @param task
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/list")
    @RequiresPermissions("system:task:task")
    public Result list(Page<Task> page, Task task) {
        return taskService.selectTaskByPage(page,task);
    }

    /**
     * 添加页面
     * @return
     */
    @GetMapping(value = "/add")
    @RequiresPermissions("system:task:add")
    public String add() {
        return "system/task/add";
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    @RequiresPermissions("system:task:edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Task task = taskService.selectTaskByID(id);
        model.addAttribute("task", task);
        return "system/task/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping(value = "/save")
    @RequiresPermissions("system:task:add")
    public Result saveTask(Task task) {
        return taskService.saveTask(task);
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping(value = "/update")
    @RequiresPermissions("system:task:edit")
    public Result update(Task task) {
        return taskService.updateTask(task);
    }

    /**
     * 删除
     */
    @ResponseBody
    @PostMapping(value = "/remove")
    @RequiresPermissions("system:task:remove")
    public Result remove(Integer id) {
        return taskService.removeTask(id);
    }

    /**
     * 停止 启动任务
     */
    @ResponseBody
    @PostMapping(value = "/changeJobStatus")
    @RequiresPermissions("system:task:change")
    public Result changeJobStatus(Integer id,String cmd ) {
        String label = "停止";
        if ("start".equals(cmd)) {
            label = "启动";
        } else {
            label = "停止";
        }
        try {
            taskService.changeStatus(id, cmd);
            return Result.ok("任务" + label + "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok("任务" + label + "失败");
    }


}
