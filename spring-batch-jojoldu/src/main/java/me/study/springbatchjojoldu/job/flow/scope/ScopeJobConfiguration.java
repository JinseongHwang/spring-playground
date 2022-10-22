package me.study.springbatchjojoldu.job.flow.scope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ScopeJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final SimpleJobTasklet simpleJobTasklet;

    @Bean
    public Job scopeJob() {
        return jobBuilderFactory.get("scopeJob")
                                .start(scopeStep1(null))
                                .next(scopeStep2(null))
                                .next(scopeStep3())
                                .build();
    }

    @Bean
    @JobScope
    public Step scopeStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("scopeStep1")
                                 .tasklet((contribution, chunkContext) -> {
                                     log.info(">>>>> This is scopeStep1");
                                     log.info(">>>>> requestDate = {}", requestDate);
                                     return RepeatStatus.FINISHED;
                                 })
                                 .build();
    }

    @Bean
    @JobScope
    public Step scopeStep2(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return stepBuilderFactory.get("scopeStep2")
                                 .tasklet(scopeStep2Tasklet(null))
                                 .build();
    }

    @Bean
    @StepScope
    public Tasklet scopeStep2Tasklet(@Value("#{jobParameters[requestDate]}") String requestDate) {
        return (contribution, chunkContext) -> {
            log.info(">>>>> This is scopeStep2");
            log.info(">>>>> requestDate = {}", requestDate);
            return RepeatStatus.FINISHED;
        };
    }

    public Step scopeStep3() {
        return stepBuilderFactory.get("scopeStep3")
                                 .tasklet(simpleJobTasklet)
                                 .build();
    }
}
