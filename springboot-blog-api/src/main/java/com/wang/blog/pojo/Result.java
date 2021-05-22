package com.wang.blog.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/2 9:29
 */
@Data
@NoArgsConstructor
public class Result {
    public Integer code;
    public String message;
    public Object object;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }
}
