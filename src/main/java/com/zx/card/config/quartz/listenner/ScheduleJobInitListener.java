package com.zx.card.config.quartz.listenner;

import com.zx.card.config.quartz.utils.QuartzManager;
import com.zx.card.system.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ScheduleJobInitListener implements CommandLineRunner {

	@Autowired
	private TaskService scheduleJobService;

	@Autowired
	QuartzManager quartzManager;

	@Override
	public void run(String... arg0) {
		try {
			scheduleJobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}