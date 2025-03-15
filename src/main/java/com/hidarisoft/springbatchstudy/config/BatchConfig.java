package com.hidarisoft.springbatchstudy.config;


import com.hidarisoft.springbatchstudy.tasklet.FirstTasklet;
import com.hidarisoft.springbatchstudy.tasklet.SecondTasklet;
import com.hidarisoft.springbatchstudy.tasklet.ThirdTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private FirstTasklet firstTasklet;

    @Autowired
    private SecondTasklet secondTasklet;

    @Autowired
    private ThirdTasklet thirdTasklet;

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(firstTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .tasklet(secondTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step step3() {
        return new StepBuilder("step3", jobRepository)
                .tasklet(thirdTasklet, transactionManager)
                .build();
    }

    @Bean
    public Job job1() {
        return new JobBuilder("job1", jobRepository)
                .start(step1())
                .build();
    }

    @Bean
    public Job job2() {
        return new JobBuilder("job2", jobRepository)
                .start(step2())
                .build();
    }

    @Bean
    public Job job3() {
        return new JobBuilder("job3", jobRepository)
                .start(step3())
                .build();
    }
}