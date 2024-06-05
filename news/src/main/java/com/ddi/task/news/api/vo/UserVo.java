package com.ddi.task.news.api.vo;

import lombok.Getter;

@Getter
public class UserVo {
    private Long userSn;
    private String email;
    private String password;
    private String name;
    private String regDate;
}