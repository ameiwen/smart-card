package com.zx.card.enums;

public enum BookEnum {

    //借书
    borrow("1"),
    //还书
    give_back("2");

    BookEnum(String code){
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
