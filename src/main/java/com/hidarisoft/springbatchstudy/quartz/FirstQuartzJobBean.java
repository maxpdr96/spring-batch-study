package com.hidarisoft.springbatchstudy.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
@Slf4j
public class FirstQuartzJobBean extends QuartzJobBean {

    private final JobLauncher jobLauncher;
    private final Job firstJob;

    public FirstQuartzJobBean(JobLauncher jobLauncher, Job firstJob) {
        this.jobLauncher = jobLauncher;
        this.firstJob = firstJob;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobParameters parameters = new JobParametersBuilder()
                .addString("first", UUID.randomUUID().toString())
                .toJobParameters();

        try {
            jobLauncher.run(firstJob, parameters);
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobRestartException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}