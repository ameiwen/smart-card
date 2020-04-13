package com.zx.card.system.service;

import com.github.pagehelper.Page;
import com.zx.card.system.model.Task;
import com.zx.card.utils.Result;

public interface TaskService {

    Result selectTaskByPage(Page<Task> pageInfo, Task task);

    Task selectTaskByID(Integer id);

    Result saveTask(Task task);

    Result updateTask(Task task);

    Result removeTask(Integer id);

    void initSchedule();

    void changeStatus(Integer jobId, String cmd);

}
