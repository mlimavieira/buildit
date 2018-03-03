package com.mlimavieira.buildit;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;

@Configuration
public class Config {

	private static final String MONGO_DB_URL = "localhost";
	private static final String MONGO_DB_NAME = "buildit";

	@Bean
	public MongoTemplate mongoTemplate() throws IOException {
		EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
		mongo.setBindIp(MONGO_DB_URL);
		mongo.setPort(9000);
		
		MongoClient mongoClient = mongo.getObject();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
		return mongoTemplate;
	}

}
