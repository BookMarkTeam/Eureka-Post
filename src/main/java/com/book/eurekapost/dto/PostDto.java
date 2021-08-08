package com.book.eurekapost.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
public class PostDto {
    private Long id;
    private String title;
    private String contents;
    private String links;
    private String userName;
    private Date createdAt;
    private Integer hit;
    private Integer likeCount;
}
