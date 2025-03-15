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
public class SecondQuartzJobBean extends QuartzJobBean {

    private final JobLauncher jobLauncher;
    private final Job secondJob;

    public SecondQuartzJobBean(JobLauncher jobLauncher, Job secondJob) {
        this.jobLauncher = jobLauncher;
        this.secondJob = secondJob;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobParameters parameters = new JobParametersBuilder()
                .addString("second", UUID.randomUUID().toString())
                .toJobParameters();

        try {
            jobLauncher.run(secondJob, parameters);
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobRestartException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}