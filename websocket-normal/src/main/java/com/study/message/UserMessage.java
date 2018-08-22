package com.study.message;

import com.study.utils.UserOptions;

public class UserMessage implements Message {
    private final String type = "user";
    private String name;
    private UserOptions option;
    private String allUserNames;

    public UserMessage() {

    }

    public UserMessage(String name, String option) {
        this.name = name;
        setOption(option);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public String getOption() {
        return option.getOption();
    }

    public void setOption(String option) {
        switch (option) {
            case "DELETE" : this.option = UserOptions.DELETE;
            break;
            case "ADD" : this.option = UserOptions.ADD;
        }
    }

    public String getAllUserNames() {
        return allUserNames;
    }

    public void setAllUserNames(String allUserNames) {
        this.allUserNames = allUserNames;
    }
}
