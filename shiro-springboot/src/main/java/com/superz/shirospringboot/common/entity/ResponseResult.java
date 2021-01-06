package com.superz.shirospringboot.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang.chao
 * @date 2021/1/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    public static <E> ResponseResult<E> success(E data) {
        return new ResponseResult<>(0, "success", data);
    }
}
