package com.study.utils;

import java.util.Map;

public class MapUtils {

    /**
     * Get the value from a map, the key of map must be String
     * @param map
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getMapValue(Map<String, T> map, String key) {
        if (null != map && map.size() > 0) {
            return map.get(key);
        }
        return null;
    }
}
