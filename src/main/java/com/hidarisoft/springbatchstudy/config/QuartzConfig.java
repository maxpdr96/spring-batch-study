package com.hidarisoft.springbatchstudy.config;

import com.hidarisoft.springbatchstudy.quartz.FirstQuartzJobBean;
import com.hidarisoft.springbatchstudy.quartz.SecondQuartzJobBean;
import com.hidarisoft.springbatchstudy.quartz.ThirdQuartzJobBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class QuartzConfig {

    @Bean
    public JobDetail quartzJobDetailFirstJob() {
        return JobBuilder.newJob(FirstQuartzJobBean.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger firstJobExecuted() {
        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetailFirstJob())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();
    }


    @Bean
    public JobDetail quartzJobDetailSecondJob() {
        return JobBuilder.newJob(SecondQuartzJobBean.class)
                .storeDurably()
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger secondJobExecuted() {

        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetailSecondJob())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(2)
                        .repeatForever())
                .build();

    }

    @Bean
    public JobDetail quartzJobDetailThirdJob() {
        return JobBuilder.newJob(ThirdQuartzJobBean.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger thirdJobExecuted() {
        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetailThirdJob())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(3)
                        .repeatForever())
                .build();
    }
}