package com.zx.card.enums;

public enum SexEnum {
    //男
    boy("1"),
    //女
    girl("2");

    private String code;

    SexEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
