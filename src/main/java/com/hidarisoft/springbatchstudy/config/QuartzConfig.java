package com.hidarisoft.springbatchstudy.config;

import com.hidarisoft.springbatchstudy.quartz.BatchJobQuartzJobBean;
import org.quartz.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    private final ApplicationContext applicationContext;
    private final JobLauncher jobLauncher;
    private final Job job1;
    private final Job job2;
    private final Job job3;

    public QuartzConfig(
            ApplicationContext applicationContext,
            JobLauncher jobLauncher,
            @Qualifier("job1") Job job1,
            @Qualifier("job2") Job job2,
            @Qualifier("job3") Job job3) {
        this.applicationContext = applicationContext;
        this.jobLauncher = jobLauncher;
        this.job1 = job1;
        this.job2 = job2;
        this.job3 = job3;
    }

    @Bean
    public JobDetail job1Detail() {
        return JobBuilder.newJob(BatchJobQuartzJobBean.class)
                .withIdentity("job1Detail")
                .storeDurably()
                .usingJobData("jobName", job1.getName())
                .build();
    }

    @Bean
    public JobDetail job2Detail() {
        return JobBuilder.newJob(BatchJobQuartzJobBean.class)
                .withIdentity("job2Detail")
                .storeDurably()
                .usingJobData("jobName", job2.getName())
                .build();
    }

    @Bean
    public JobDetail job3Detail() {
        return JobBuilder.newJob(BatchJobQuartzJobBean.class)
                .withIdentity("job3Detail")
                .storeDurably()
                .usingJobData("jobName", job3.getName())
                .build();
    }

    @Bean
    public Trigger job1Trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(job1Detail())
                .withIdentity("job1Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(2)
                        .repeatForever())
                .build();
    }

    @Bean
    public Trigger job2Trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(job2Detail())
                .withIdentity("job2Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(4)
                        .repeatForever())
                .build();
    }

    @Bean
    public Trigger job3Trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(job3Detail())
                .withIdentity("job3Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(6)
                        .repeatForever())
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // Configurar o JobFactory para injetar dependências nos jobs
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);

        schedulerFactoryBean.setTriggers(job1Trigger(), job2Trigger(), job3Trigger());
        schedulerFactoryBean.setJobDetails(job1Detail(), job2Detail(), job3Detail());
        return schedulerFactoryBean;
    }
}