package com.hidarisoft.springbatchstudy.tasklet;
import com.hidarisoft.springbatchstudy.service.SecondService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondTasklet implements Tasklet {

    private final SecondService secondService;

    @Autowired
    public SecondTasklet(SecondService secondService) {
        this.secondService = secondService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        secondService.execute();
        return RepeatStatus.FINISHED;
    }
}