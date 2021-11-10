package com.sparta.devlogspring.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class AppConfig {

    public static @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb://admin:rew748596@3.35.149.46:27017");
    }

    public static @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "member_card");
    }

}