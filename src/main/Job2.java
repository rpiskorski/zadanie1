package main;


import org.quartz.*;



public class Job2 implements org.quartz.Job{


    public void execute(JobExecutionContext context)throws JobExecutionException
    {
        try {
            database.checktime();
        }catch(Exception e)
        {
            e.printStackTrace();
        }


    }

}
