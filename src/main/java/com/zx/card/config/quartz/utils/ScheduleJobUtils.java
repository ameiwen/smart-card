package com.zx.card.config.quartz.utils;


import com.zx.card.config.quartz.bean.ScheduleJob;
import com.zx.card.system.model.Task;

public class ScheduleJobUtils {
	public static ScheduleJob entityToData(Task scheduleJobEntity) {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
		scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
		scheduleJob.setDescription(scheduleJobEntity.getDescription());
		scheduleJob.setJobName(scheduleJobEntity.getJobName());
		scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
		scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
		return scheduleJob;
	}
}