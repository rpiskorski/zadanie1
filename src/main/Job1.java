package main;


import org.quartz.*;

public class Job1 implements org.quartz.Job {

    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        database.savetofile();
    }
}
