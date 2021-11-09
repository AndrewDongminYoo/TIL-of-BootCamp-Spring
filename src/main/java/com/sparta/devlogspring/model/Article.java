package com.sparta.devlogspring.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Document
@RequiredArgsConstructor
public class Article {

    @Id
    private ObjectId id;
    private final String name;
    private final String author;
    private final String title;
    private final String siteName;
    private final String url;
    private final String description;
    private final String image;
    private final LocalDateTime registered;
    private final LocalDateTime modified;
    private final int shared;
    private final int comment;
}
