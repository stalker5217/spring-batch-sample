package com.example.springbatch.hello;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class HelloJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job helloJob() {
		return jobBuilderFactory
			.get("helloJob")
			.start(helloStep1())
			.next(helloStep2())
			.build();
	}

	@Bean
	public Step helloStep1() {
		return stepBuilderFactory
			.get("helloStep1")
			.tasklet(((contribution, chunkContext) -> {
				log.info("Hello Spring Batch!");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}

	@Bean
	public Step helloStep2() {
		return stepBuilderFactory
			.get("helloStep2")
			.tasklet(((contribution, chunkContext) -> {
				log.info("Hello~");
				return RepeatStatus.FINISHED;
			}))
			.build();
	}
}
