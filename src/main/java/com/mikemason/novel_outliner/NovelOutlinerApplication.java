package com.mikemason.novel_outliner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NovelOutlinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovelOutlinerApplication.class, args);
	}

}
