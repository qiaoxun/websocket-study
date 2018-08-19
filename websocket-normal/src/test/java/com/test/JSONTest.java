package com.test;

import com.alibaba.fastjson.JSONObject;
import com.study.message.ChatMessage;
import com.study.message.Message;

public class JSONTest {

    public static void main(String... args) {
        Message msg = new ChatMessage("Joey", "Peter", "GFY");
        System.out.println(JSONObject.toJSONString(msg));
    }
}
