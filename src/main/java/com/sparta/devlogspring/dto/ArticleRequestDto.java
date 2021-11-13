package com.sparta.devlogspring.dto;

import lombok.Getter;
import lombok.ToString;
import org.bson.Document;

import java.time.LocalDateTime;

@Getter
@ToString
public class ArticleRequestDto {

    private final String id, name, author, title, siteName, url, description, image;
    private final LocalDateTime registered;


    public ArticleRequestDto(Document articleJson) {
        this.id = articleJson.getString("id");
        this.name = articleJson.getString("name");
        this.author = articleJson.getString("author");
        this.title = articleJson.getString("title");
        this.siteName = articleJson.getString("siteName");
        this.url = articleJson.getString("url");
        this.description = articleJson.getString("description");
        this.image = articleJson.getString("image");
        this.registered = (LocalDateTime) articleJson.get("registered");
    }

}
