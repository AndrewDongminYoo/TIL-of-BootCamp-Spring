package com.sparta.devlogspring.model;

import com.sparta.devlogspring.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private String id;

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
        System.out.println(requestDto);
        this.id = requestDto.getId();
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
