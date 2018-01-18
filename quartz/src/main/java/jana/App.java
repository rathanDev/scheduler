package jana;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class App {

    public static void main( String[] args ) {
        System.out.println( "Inside main" );

        JobDetail jobDetail = new JobDetail();
        jobDetail.setName("sampleJob");
        jobDetail.setJobClass(SampleJob.class);

        SimpleTrigger trigger = new SimpleTrigger();
        trigger.setName("trigger1");
        trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setRepeatInterval(4000);

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
