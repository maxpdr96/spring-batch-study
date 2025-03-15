package com.hidarisoft.springbatchstudy.tasklet;
import com.hidarisoft.springbatchstudy.service.ThirdService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class ThirdTasklet implements Tasklet {

    private final ThirdService thirdService;

    @Autowired
    public ThirdTasklet(ThirdService thirdService) {
        this.thirdService = thirdService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        thirdService.execute();
        return RepeatStatus.FINISHED;
    }
}