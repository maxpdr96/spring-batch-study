package com.hidarisoft.springbatchstudy.config;

import com.hidarisoft.springbatchstudy.quartz.FirstQuartzJobBean;
import com.hidarisoft.springbatchstudy.quartz.SecondQuartzJobBean;
import com.hidarisoft.springbatchstudy.quartz.ThirdQuartzJobBean;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class QuartzConfig {
    public record Tuple<A, B>(A first, B second) {
    }

    private final Scheduler scheduler;

    @PostConstruct
    public void init() throws SchedulerException {
        var first = firstJobExecuted();
        var second = secondJobExecuted();
        var third = thirdJobExecuted();

        Map<JobDetail, Set<? extends Trigger>> map = new HashMap<>();
        map.put(first.first, Set.of(first.second));
        map.put(second.first, Set.of(second.second));
        map.put(third.first, Set.of(third.second));
        scheduler.scheduleJobs(map, false);
    }

    private Tuple<JobDetail, Trigger> firstJobExecuted() {
        log.info("First init");

        JobDetail firstJobDetail = JobBuilder.newJob(FirstQuartzJobBean.class)
                .withIdentity("firstJobDetail", "settleBatch")
                .build();

        Trigger firstTrigger = TriggerBuilder
                .newTrigger()
                .forJob(firstJobDetail)
                .withIdentity("settleDaysTrigger", "settleBatch")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();

        return new Tuple<>(firstJobDetail, firstTrigger);
    }

    private Tuple<JobDetail, Trigger> secondJobExecuted() {
        log.info("Second init");

        JobDetail secondJobDetail = JobBuilder.newJob(SecondQuartzJobBean.class)
                .withIdentity("secondJobDetail", "settleBatch")
                .build();

        Trigger secondTrigger = TriggerBuilder
                .newTrigger()
                .forJob(secondJobDetail)
                .withIdentity("settleDaysTrigger", "settleBatch")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(2)
                        .repeatForever())
                .build();

        return new Tuple<>(secondJobDetail, secondTrigger);
    }

    private Tuple<JobDetail, Trigger> thirdJobExecuted() {
        log.info("Third init");

        JobDetail thirdJobDetail = JobBuilder.newJob(ThirdQuartzJobBean.class)
                .withIdentity("thirdJobDetail", "settleBatch")
                .build();

        Trigger thirdTrigger = TriggerBuilder
                .newTrigger()
                .forJob(thirdJobDetail)
                .withIdentity("settleDaysTrigger", "settleBatch")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(3)
                        .repeatForever())
                .build();

        return new Tuple<>(thirdJobDetail, thirdTrigger);
    }
}