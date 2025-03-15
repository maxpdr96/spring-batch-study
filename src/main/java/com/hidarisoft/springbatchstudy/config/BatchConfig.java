package com.hidarisoft.springbatchstudy.config;


import com.hidarisoft.springbatchstudy.tasklet.FirstTasklet;
import com.hidarisoft.springbatchstudy.tasklet.SecondTasklet;
import com.hidarisoft.springbatchstudy.tasklet.ThirdTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {
    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job firstJob(Step simpleStep) {
        return new JobBuilder("firstJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleStep)
                .build();
    }

    @Bean
    public Step simpleStep() {
        return new StepBuilder("simpleStep", jobRepository)
                .tasklet((a, b) -> {
                    log.info("simpleStep start");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

    @Bean
    public Job secondJob(Step secondStep) {
        return new JobBuilder("secondJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(secondStep)
                .build();
    }

    @Bean
    public Step secondStep() {
        return new StepBuilder("secondStep", jobRepository)
                .tasklet((a, b) -> {
                    log.info("secondStep start");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }

    @Bean
    public Job thirdJob(Step thirdStep) {
        return new JobBuilder("thirdJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(thirdStep)
                .build();
    }

    @Bean
    public Step thirdStep() {
        return new StepBuilder("thirdStep", jobRepository)
                .tasklet((a, b) -> {
                    log.info("thirdStep start");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager)
                .build();
    }
}