package com.zx.card.enums;

public enum  EventEnums {

    //食堂
    canteen("1"),
    //超市
    supermarket("2"),
    //充值
    deposit("3"),
    //其他
    other("4");

    EventEnums(String code) {
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
