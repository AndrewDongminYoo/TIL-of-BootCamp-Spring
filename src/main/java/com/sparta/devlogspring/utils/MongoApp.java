package com.sparta.devlogspring.utils;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoApp {

    private final static MongoClient mongoClient = MongoClients.create("mongodb://admin:rew748596@3.35.149.46:27017");
    private final static MongoOperations mongoOps = new MongoTemplate(mongoClient, "member_card");
    private final static MongoCollection<Document> articlesCol = mongoOps.getCollection("articles");
    private final static MongoCollection<Document> membersCol = mongoOps.getCollection("members");

    public static void main(String[] args) {

        FindIterable<Document> articles = articlesCol.find();
        for (Document article: articles) {
            System.out.println(article.toJson());
        }
        FindIterable<Document> members = membersCol.find();
        for (Document member: members) {
            System.out.println(member.toJson());
        }
    }
}