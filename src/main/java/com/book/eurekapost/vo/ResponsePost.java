package com.book.eurekapost.vo;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
public class ResponsePost {
    private Long id;
    private String title;
    private String contents;
    private String userName;
    private String links;
    private Date createdAt;
    private Integer hit;
    private Integer likeCount;
    // 댓글 추가 예정
}
