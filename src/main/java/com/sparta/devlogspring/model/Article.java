package com.sparta.devlogspring.model;

import com.sparta.devlogspring.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Article {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private UUID id;

    @Column
    private String name;
    @Column
    private String author;
    @Column
    private String title;
    @Column
    private String siteName;
    @Column
    private String url;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private LocalDateTime registered;
    @Column
    private int shared, comment;

    public Article(ArticleRequestDto requestDto) {
        this.name = requestDto.getName();
        this.author = requestDto.getAuthor();
        this.title = requestDto.getTitle();
        this.siteName = requestDto.getSiteName();
        this.url = requestDto.getUrl();
        this.description = requestDto.getDescription();
        this.image = requestDto.getImage();
        this.registered = requestDto.getRegistered();
        this.shared = 0;
        this.comment = 0;
    }
}
