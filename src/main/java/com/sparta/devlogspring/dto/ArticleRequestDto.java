package com.sparta.devlogspring.dto;

import com.sparta.devlogspring.utils.ArticleCrawler;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Getter
@ToString
public class ArticleRequestDto {

    private final String name, author, title, siteName, url, description, image;
    private final LocalDateTime registered;

    public ArticleRequestDto(JSONObject articleJson) {
        this.name = articleJson.getString("name");
        this.author = articleJson.getString("author");
        this.title = articleJson.getString("title");
        this.siteName = articleJson.getString("site_name");
        this.url = articleJson.getString("url");
        this.description = articleJson.getString("description");
        this.image = articleJson.getString("image");
        this.registered = ArticleCrawler.getLocalTime(articleJson.getString("registered"));
    }
}
