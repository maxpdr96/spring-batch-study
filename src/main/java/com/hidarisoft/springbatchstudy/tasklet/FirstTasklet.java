package com.hidarisoft.springbatchstudy.tasklet;

import com.hidarisoft.springbatchstudy.service.FirstService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstTasklet implements Tasklet {

    private final FirstService firstService;

    @Autowired
    public FirstTasklet(FirstService firstService) {
        this.firstService = firstService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        firstService.execute();
        return RepeatStatus.FINISHED;
    }
}