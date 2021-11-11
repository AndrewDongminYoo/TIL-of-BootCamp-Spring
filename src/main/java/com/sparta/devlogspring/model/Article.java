package com.sparta.devlogspring.model;

import com.sparta.devlogspring.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Getter
@Document
@RequiredArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field private String name, author, title, siteName, url, description, image;
    @Field private LocalDateTime registered;
    @Field private int shared, comment;

    public void update(ArticleRequestDto requestDto) {
        this.name = requestDto.getName();
        this.author = requestDto.getAuthor();
        this.title = requestDto.getTitle();
        this.siteName = requestDto.getSiteName();
        this.url = requestDto.getUrl();
        this.description = requestDto.getDescription();
        this.image = requestDto.getImage();
    }
}
