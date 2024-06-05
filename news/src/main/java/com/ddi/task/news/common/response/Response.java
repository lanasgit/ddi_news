package com.ddi.task.news.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response<T> {

    // 처리 결과 상태 (default = "success")
    private String status;

    // 메시지 (default = "성공")
    private String message;

    // 응답 code (default = "0000")
    private String code;

    // 응답 data
    private T data;

    public static <T> Response<T> success(String message) {
        return Response.<T>builder()
                .status("success")
                .code("0000")
                .message(message)
                .build();
    }

    public static <T> Response<T> success(T data) {
        return Response.<T>builder()
                .status("success")
                .code("0000")
                .message("성공")
                .data(data)
                .build();
    }

    public static <T> Response<T> success(String message, T data) {
        return Response.<T>builder()
                .status("success")
                .code("0000")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> Response<T> error(String message, String code) {
        return Response.<T>builder()
                .status("fail")
                .code(code)
                .message(message)
                .build();
    }

    public static <T> Response<T> error(String message) {
        return Response.<T>builder()
                .status("fail")
                .code("9999")
                .message(message)
                .build();
    }

}