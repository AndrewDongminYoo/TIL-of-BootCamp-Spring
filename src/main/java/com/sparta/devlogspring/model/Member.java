package com.sparta.devlogspring.model;

import com.sparta.devlogspring.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.annotations.Columns;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Member(MemberRequestDto requestDto) {
        this.id = new ObjectId().toString();
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
