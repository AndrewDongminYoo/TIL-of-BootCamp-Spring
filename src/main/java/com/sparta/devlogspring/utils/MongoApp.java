package com.sparta.devlogspring.utils;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.sparta.devlogspring.dto.ArticleRequestDto;
import com.sparta.devlogspring.model.Article;
import com.sparta.devlogspring.model.ArticleJpaRepository;
import com.sparta.devlogspring.model.MemberJpaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class MongoApp {

    private final static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private final static MongoOperations mongoOps = new MongoTemplate(mongoClient, "member_card");
    private final static MongoCollection<Document> articlesCol = mongoOps.getCollection("articles");
    private final static MongoCollection<Document> membersCol = mongoOps.getCollection("members");
    private final ArticleJpaRepository articleRepository;
    private final MemberJpaRepository memberRepository;

    public static void printAll() {
        FindIterable<Document> articles = articlesCol.find();
        for (Document article: articles) {
            System.out.println(article.toJson());
        }
        FindIterable<Document> members = membersCol.find();
        for (Document member: members) {
            System.out.println(member.toJson());
        }
    }

    public static void main(String[] args) {
        FindIterable<Document> articles = articlesCol.find();
        for (Document article: articles) {
            JSONObject jsonObj = new JSONObject(article);
            ArticleRequestDto requestDto = new ArticleRequestDto(jsonObj);
        }
    }
}