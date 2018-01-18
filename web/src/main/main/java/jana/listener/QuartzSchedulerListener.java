package jana.listener;

import jana.quartz.SchedulerJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class QuartzSchedulerListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		//
	}

	public void contextInitialized(ServletContextEvent arg0) {

		JobDetail job = JobBuilder.newJob(SchedulerJob.class)
				.withIdentity("anyJobName", "group1").build();

		try {

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity("anyTriggerName", "group1")
					.withSchedule(
							CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
					.build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}