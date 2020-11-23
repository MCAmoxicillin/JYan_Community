package com.jyan.result;

import lombok.Data;

@Data
public class ArticleForm {
    private String title;
    private Integer categoryId;
    private String content;
    private String userNetname;
    private String userImg;
    private Integer userId;
}
