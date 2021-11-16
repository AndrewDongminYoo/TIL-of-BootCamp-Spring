package com.sparta.devlogspring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class ArticleRequestDto {

    private final String id, name, author, title, siteName, url, description, image;
    private final LocalDateTime registered;
    private final int comment, shared;


    public ArticleRequestDto(JSONObject articleJson) {
        StringBuilder sb = new StringBuilder();
        String input = articleJson.toString();
        for (int i=0; i<input.length(); i++) {
            if (i<(input.length()-1)) {
                if (Character.isSurrogatePair(input.charAt(i), input.charAt(i+1))) {
                    i += 1;
                    continue;
                }
                sb.append(input.charAt(i));
            }
        }
        sb.append("}");
        articleJson = new JSONObject(sb.toString());
        this.id = articleJson.getString("id");
        this.name = articleJson.getString("name");
        this.author = articleJson.getString("author");
        this.title = articleJson.getString("title");
        this.siteName = articleJson.getString("siteName");
        this.url = articleJson.getString("url");
        this.description = articleJson.getString("description");
        this.image = articleJson.getString("image");
        this.registered = LocalDateTime.parse(articleJson.getString("registered"));
        this.comment = 0;
        this.shared = 0;
    }

}
