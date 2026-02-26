package org.example.practice001.common.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static org.example.practice001.common.model.BaseResponseStatus.SUCCESS;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse<T> {
    private Boolean isSuccess;
    private T result;

    public static <T> BaseResponse success(T result) {
        return new BaseResponse(
                SUCCESS.isSuccess(),
                result
        );
    }

    public static <T> BaseResponse fail(BaseResponseStatus status) {
        return new BaseResponse(
                status.isSuccess(),
                null
        );
    }

    public static <T> BaseResponse fail(BaseResponseStatus status, T result) {
        return new BaseResponse(
                status.isSuccess(),
                result
        );
    }
}