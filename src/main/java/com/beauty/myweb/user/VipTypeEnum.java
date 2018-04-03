package com.beauty.myweb.user;

public enum VipTypeEnum {

    DAY("day","天"),
    MONTH("month","月"),
    QUARTER("quarter","季度"),
    YEAR("year","年"),
    FOREVER("forever","永久");

    private String code;

    private String name;

    private VipTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
