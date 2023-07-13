package com.devstack.pos.view.tm;

public class productTm {
    private int code;
    private String description;

    public productTm() {
    }

    public productTm(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
