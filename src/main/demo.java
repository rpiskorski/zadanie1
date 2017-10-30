package main;

import java.util.Scanner;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class demo {

    public static void main(String args[]) throws InterruptedException
    {

        try {
            JobDetail job1 = JobBuilder.newJob(Job1.class)
                    .withIdentity("save", "group1")
                    .usingJobData("job1",1)
                    .build();


            CronTrigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * *"))
                    .build();

            JobDetail job2 = JobBuilder.newJob(Job2.class)
                    .withIdentity("time", "group2")
                    .usingJobData("job2",2)
                    .build();


            CronTrigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group2")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 * 8-19 ? * MON,TUE,WED,THU,FRI"))
                    .build();

            Scheduler scheduler= new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job1, trigger1);
            scheduler.scheduleJob(job2, trigger2);



            String answer;
            String number;
            Scanner buff=new Scanner(System.in);

            for(int i=1;i<11;i++) {
                System.out.println("Podaj nr zadania a w nastepnej linii odpowiedź ");

                number = buff.nextLine();
                answer = buff.nextLine();
                try {
                    database.set_answer(number, answer);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }


            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }






        }
    }

