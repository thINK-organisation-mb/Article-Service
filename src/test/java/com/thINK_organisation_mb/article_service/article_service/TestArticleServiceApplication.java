package com.thINK_organisation_mb.article_service.article_service;

import org.springframework.boot.SpringApplication;

public class TestArticleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ArticleServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
