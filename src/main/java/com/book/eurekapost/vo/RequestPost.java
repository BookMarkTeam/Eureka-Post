package com.book.eurekapost.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RequestPost {
    private Long id;
    @NotNull(message = "Title can't be null")
    private String title;
    @NotNull(message = "Contents can't be null")
    private String contents;
    @NotNull(message = "Links can't be null")
    private String links;
    @NotNull(message = "Username can't be null")
    private String userName;
    private Integer hit;
    private Integer likeCount;
    private Date createAt;
}
