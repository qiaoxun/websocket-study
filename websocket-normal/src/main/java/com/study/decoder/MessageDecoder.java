package com.study.decoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.message.ChatMessage;
import com.study.message.JoinMessage;
import com.study.message.Message;
import com.study.message.UserMessage;
import com.study.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Decode a JSON message into a JoinMessage or a chatMessage
 * For example, the incoming message
 * {"type": "chat", "name": "Peter", "target": "Duke", "message": "How are you?"}
 *  is decoded as (new ChatMessage("Peter", "Duke", "How are you?"))
 */
public class MessageDecoder implements Decoder.Text<Message> {
    /* Stores the name-pairs from a JSON message as a map */
    private Map<String, String> messageMap = new HashMap<>();

    /**
     * Create new Message object if the message could be decoded
     * @param s
     * @return
     * @throws DecodeException
     */
    @Override
    public Message decode(String s) throws DecodeException {
        Message msg = null;
        if (willDecode(s)) {
            String type = MapUtils.getMapValue(messageMap, "type");
            if (StringUtils.isBlank(type)) {
                throw new RuntimeException("Type can not be null");
            }

            System.out.println("decode type = " + type);

            switch(type) {
                case "join" :
                    msg = new JoinMessage(MapUtils.getMapValue(messageMap, "name"));
                    break;
                case "chat" :
                    msg = new ChatMessage(MapUtils.getMapValue(messageMap, "name"),
                            MapUtils.getMapValue(messageMap, "target"),
                            MapUtils.getMapValue(messageMap, "message"));
                    break;
            }
        } else {
            throw new DecodeException(s, "[Message] can't be decode");
        }
        return msg;
    }

    /**
     *  Decode a JSON message into a Map and check if it contains all
     *  the required fields according to its type
     * @param str
     * @return
     */
    @Override
    public boolean willDecode(String str) {
        System.out.println("will Decode + " + str);
        // Convert JSON data from the message into a name-value map..
        // Check if the message has all the field for its message type
        JSONObject json = JSONObject.parseObject(str);
        System.out.println("json " + json);
        // Do some check
        // TODO
//        String type = (String) MapUtils.getMapValue(json, "type");
//        if (StringUtils.isNotBlank(type)) {
//            switch (type) {
//                case "chat" :
//                    String name = (String) MapUtils.getMapValue(json, "type");
//            }
//        } else {
//            return false;
//        }

        BiConsumer<String, Object> bi = (k, v) -> messageMap.put(k, v.toString());
        json.forEach(bi);
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }


}
