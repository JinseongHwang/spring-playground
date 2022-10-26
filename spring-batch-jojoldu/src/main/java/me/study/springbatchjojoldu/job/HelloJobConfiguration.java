package me.study.springbatchjojoldu.job;

import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.study.springbatchjojoldu.entity.Pay;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int chunkSize = 10;
    private static final String JOB_NAME = "helloJob";
    private static final String STEP_NAME = JOB_NAME + "_Step";

    private static Integer a = Integer.valueOf(10);

    @Bean(JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                                .incrementer(new RunIdIncrementer())
                                .start(step())
                                .build();
    }

    @Bean(STEP_NAME)
    @JobScope
    public Step step() {
        return stepBuilderFactory.get(STEP_NAME)
                                 .<Pay, String>chunk(chunkSize)
                                 .reader(reader())
                                 .processor(new ItemProcessor<Pay, String>() {

                                     final RepeatTemplate repeatTemplate = new RepeatTemplate();

                                     @Override
                                     public String process(Pay item) throws Exception {
                                         repeatTemplate.setCompletionPolicy(new SimpleCompletionPolicy(3));
                                         repeatTemplate.iterate(new RepeatCallback() {
                                             @Override
                                             public RepeatStatus doInIteration(RepeatContext context) throws Exception {
                                                 System.out.println(item.getTxName() + "_repeat");
                                                 return RepeatStatus.CONTINUABLE;
                                             }
                                         });
                                         return item.toString();
                                     }
                                 })
                                 .writer(writer())
                                 .build();
    }

    @Bean(STEP_NAME + "_reader")
    @StepScope
    public ItemReader<Pay> reader() {
        return new JpaPagingItemReaderBuilder<Pay>()
            .name(STEP_NAME + "_reader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(chunkSize)
            .queryString("SELECT p FROM Pay p")
            .build();
    }



    @Bean(STEP_NAME + "_writer")
    @StepScope
    public ItemWriter<String> writer() {
        return item -> System.out.println(item.toString());
    }
}
