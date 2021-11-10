package com.sparta.devlogspring.utils;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoApp {

    public static void main(String[] args) {

        MongoClient mongoClient = MongoClients.create("mongodb://admin:rew748596@3.35.149.46:27017");
        MongoOperations mongoOps = new MongoTemplate(mongoClient, "member_card");
        MongoCollection<Document> articlesCol = mongoOps.getCollection("articles");
        FindIterable<Document> articles = articlesCol.find();
        for (Document article: articles) {
            System.out.println(article.toJson());
        }
    }
}