package com.book.eurekapost.dao;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String links;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = false)
    private Integer hit;
    @Column(nullable = false)
    private Integer likeCount;
    // 댓글도 추가해야할 것 같음
}
