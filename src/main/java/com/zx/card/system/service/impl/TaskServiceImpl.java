package com.zx.card.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.card.common.Constants;
import com.zx.card.config.quartz.bean.ScheduleJob;
import com.zx.card.config.quartz.utils.QuartzManager;
import com.zx.card.config.quartz.utils.ScheduleJobUtils;
import com.zx.card.system.dao.TaskMapper;
import com.zx.card.system.model.Task;
import com.zx.card.system.service.TaskService;
import com.zx.card.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public Result selectTaskByPage(Page<Task> pageInfo, Task task) {
        Map<String,Object> search = new HashMap<>();
        if(StringUtils.isNotBlank(task.getJobName())){
            search.put("jobName",task.getJobName());
        }
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        List<Task> list = taskMapper.selectTaskListWhere(search);
        PageInfo<Task> infos = new PageInfo<Task>(list);
        Result result = Result.ok("操作成功");
        result.put("data",infos);
        return result;
    }

    @Override
    public Task selectTaskByID(Integer id) {
       return taskMapper.selectTaskByPrimaryKey(id);
    }

    @Override
    public Result saveTask(Task task) {
        if(taskMapper.insertTaskSelective(task) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result updateTask(Task task) {
        if(taskMapper.updateTaskByPrimaryKeySelective(task) > 0){
            return Result.ok("操作成功");
        }
        return Result.error("系统错误");
    }

    @Override
    public Result removeTask(Integer id) {
        try {
            Task task = taskMapper.selectTaskByPrimaryKey(id);
            if (taskMapper.deleteTaskByPrimaryKey(id) > 0) {
                quartzManager.deleteJob(ScheduleJobUtils.entityToData(task));
                return Result.ok("操作成功");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return Result.error("系统错误");
    }

    @Override
    public void initSchedule() {
        // 这里获取任务信息数据
        List<Task> jobList = taskMapper.selectTaskListWhere(new HashMap<String, Object>(16));
        for (Task scheduleJob : jobList) {
            if ("1".equals(scheduleJob.getJobStatus())) {
                ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
                quartzManager.addJob(job);
            }

        }
    }

    @Override
    public void changeStatus(Integer jobId, String cmd) {
        try {
            Task scheduleJob = taskMapper.selectTaskByPrimaryKey(jobId);
            if (scheduleJob == null) {
                return;
            }
            if (Constants.STATUS_RUNNING_STOP.equals(cmd)) {
                quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
                scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
            } else {
                if (!Constants.STATUS_RUNNING_START.equals(cmd)) {
                } else {
                    scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                    quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
                }
            }
            taskMapper.updateTaskByPrimaryKeySelective(scheduleJob);

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
