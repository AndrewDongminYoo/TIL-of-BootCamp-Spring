package com.sparta.devlogspring.dto;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ArticleRequestDtoTest {
    @Test
    void isEmoji() {
        JSONObject articleJson = new JSONObject();
        articleJson.put("image", "https://media.vlpt.us/images/dhk22/post/03b5fa47-c0bd-48f9-9bf1-3c67fdfb079f/image.png?w=768");
        articleJson.put("author", "dhk22");
        articleJson.put("name", "김대현");
        articleJson.put("siteName", "dhk22.log");
        articleJson.put("description", "👼springboot 토이프로젝트 시작~");
        articleJson.put("registered", LocalDateTime.parse("2021-11-11T21:13:04.366"));
        articleJson.put("id", "6193a05054584e7417a31544");
        articleJson.put("title", "🔥 TIL - Day 52");
        articleJson.put("url", "https://velog.io/@dhk22/TIL-Day-52-hiir3lzk");
        System.out.println(articleJson);
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
        String id = articleJson.getString("id");
        String name = articleJson.getString("name");
        String author = articleJson.getString("author");
        String title = articleJson.getString("title");
        String siteName = articleJson.getString("siteName");
        String url = articleJson.getString("url");
        String description = articleJson.getString("description");
        String image = articleJson.getString("image");
        LocalDateTime registered = LocalDateTime.parse(articleJson.getString("registered"));
        System.out.println(id);
        System.out.println(name);
        System.out.println(author);
        System.out.println(title);
        System.out.println(siteName);
        System.out.println(url);
        System.out.println(description);
        System.out.println(image);
        System.out.println(registered);
    }
}