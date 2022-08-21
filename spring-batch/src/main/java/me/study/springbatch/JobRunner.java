package me.study.springbatch;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private JobLauncher jobLauncher; // EnableBatchProcessing 어노테이션이 있으면 자동으로 빈 등록이 되어 있다.

    @Autowired
    private Job job;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final JobParameters jobParameters = new JobParametersBuilder()
            .addString("name", "user1")
            .addDate("now", Date.valueOf(LocalDate.now()))
            .toJobParameters();
        jobLauncher.run(job, jobParameters);
    }
}
