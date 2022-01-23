package com.works.exceptions;

import com.works.enums.ResultEnum;

public class OurException extends RuntimeException{
    private Integer code;

    public OurException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public OurException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
