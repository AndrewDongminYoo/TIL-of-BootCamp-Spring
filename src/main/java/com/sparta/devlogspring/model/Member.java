package com.sparta.devlogspring.model;

import com.sparta.devlogspring.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name="Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String blog;

    @Column(nullable = false)
    private String blogType;

    @Column(nullable = false)
    private String siteName;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String hobby;

    @Column(nullable = false)
    private String profileImage;

    public Member(MemberRequestDto requestDto) {
        this.name = requestDto.getName();
        this.blog = requestDto.getBlog();
        this.blogType = requestDto.getBlogType();
        this.siteName = requestDto.getSiteName();
        this.specialty = requestDto.getSpecialty();
        this.hobby = requestDto.getHobby();
        this.profileImage = requestDto.getProfileImage();
    }
}
