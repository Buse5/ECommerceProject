package com.works.enums;

import lombok.Getter;

@Getter
public enum BookStatusEnum implements  CodeEnum{
    UP(0, "Available"),
    DOWN(1, "Unavailable")
    ;
    private Integer code;
    private String message;

    BookStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getStatus(Integer code) {

        for(BookStatusEnum statusEnum : BookStatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum.getMessage();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }
}
