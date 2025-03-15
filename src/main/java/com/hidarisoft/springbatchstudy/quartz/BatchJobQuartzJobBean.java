package com.hidarisoft.springbatchstudy.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class BatchJobQuartzJobBean extends QuartzJobBean {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            String jobName = context.getMergedJobDataMap().getString("jobName");

            // Log para debug
            System.out.println("Executando job: " + jobName);
            System.out.println("ApplicationContext: " + (applicationContext != null ? "disponível" : "nulo"));
            System.out.println("JobLauncher: " + (jobLauncher != null ? "disponível" : "nulo"));

            Job job = applicationContext.getBean(jobName, Job.class);

            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);

        } catch (Exception e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        }
    }
}