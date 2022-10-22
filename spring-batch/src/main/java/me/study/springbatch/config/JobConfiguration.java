package me.study.springbatch.config;

import java.util.Date;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                                .start(step1())
                                .next(step2())
                                .build();
    }

    private Step step1() {
        return stepBuilderFactory.get("step1")
                                 .tasklet((contribution, chunkContext) -> {
                                     // using contribution
                                     final JobParameters jobParameters1 = contribution.getStepExecution().getJobExecution().getJobParameters();
                                     final String name1 = jobParameters1.getString("name");
                                     final Long seq1 = jobParameters1.getLong("seq");
                                     final Date now1 = jobParameters1.getDate("now");
                                     final Double gpa1 = jobParameters1.getDouble("gpa");

                                     // using chunkContext
                                     final Map<String, Object> jobParameters2 = chunkContext.getStepContext().getJobParameters();
                                     final Object name2 = jobParameters2.get("name");
                                     final Object seq2 = jobParameters2.get("seq");
                                     final Object now2 = jobParameters2.get("now");
                                     final Object gpa2 = jobParameters2.get("gpa");

                                     System.out.println("Step1 was excuted");
                                     return RepeatStatus.FINISHED;
                                 })
                                 .build();
    }

    private Step step2() {
        return stepBuilderFactory.get("step2")
                                 .tasklet((contribution, chunkContext) -> {
                                     System.out.println("Step2 was excuted");
                                     return RepeatStatus.FINISHED;
                                 })
                                 .build();
    }
}
