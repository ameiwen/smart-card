package com.zx.card.enums;

public enum  DeptEnum {

    //院系
    faculty("1"),
    //专业
    specialty("2");

    DeptEnum(String code){
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
