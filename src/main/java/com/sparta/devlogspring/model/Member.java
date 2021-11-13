package com.sparta.devlogspring.model;

import com.sparta.devlogspring.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="Member")
public class Member {

    @Id
    @Column(nullable = false)
    private String id;

    @Column
    private String name;

    @Column
    private String blog;

    @Column
    private String blogType;

    @Column
    private String siteName;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String hobby;

    @Column
    private String profileImage;

    @Column
    private Long articles;

    public Member(MemberRequestDto requestDto) {
        System.out.println(requestDto);
        this.id = requestDto.getId();
        this.name = requestDto.getName();
        this.blog = requestDto.getBlog();
        this.blogType = requestDto.getBlogType();
        this.siteName = requestDto.getSiteName();
        this.specialty = requestDto.getSpecialty();
        this.hobby = requestDto.getHobby();
        this.profileImage = requestDto.getProfileImage();
    }

    public boolean hasBlog() {
        return this.getBlog().startsWith("http");
    }
}
