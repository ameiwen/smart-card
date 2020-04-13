package com.zx.card.enums;

public enum StatusEnum {

    //未删除||挂失
    no_delete("1"),
    //正常
    is_delete("0");

    StatusEnum(String code) {
        this.code = code;
    }
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
