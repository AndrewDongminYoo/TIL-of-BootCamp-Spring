package com.sparta.devlogspring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Document
@RequiredArgsConstructor
public class Article {

    @Id
    @MongoId(FieldType.OBJECT_ID)
    private ObjectId id;
    @Field private final String name, author, title, siteName, url, description, image;
    @Field private final LocalDateTime registered, modified;
    @Field private int shared, comment;
}
