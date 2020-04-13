package com.zx.card.enums;

public enum  RoleEnum {

    //学生
    student("0"),
    //教师
    teacher("1"),
    //其他
    other("3");

    private String code;

    RoleEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
