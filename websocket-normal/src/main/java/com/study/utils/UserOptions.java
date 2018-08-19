package com.study.utils;

public enum UserOptions {
    ADD("ADD"), DELETE("DELETE");

    private String option;

    UserOptions (String option) {
        this.option = option;
    }

    public String getOption(){
        return this.option;
    }

    @Override
    public String toString() {
        return this.getOption();
    }
}
