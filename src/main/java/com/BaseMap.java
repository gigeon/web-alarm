package com;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseMap extends HashMap<String, Object>{

    public BaseMap set(String key, Object value) {
        this.put(key, value);
        return this;
    }

    public Object getString(String key) {
        return this.get(key);
    }

    public BaseMap setMap(Map<String, String[]> inputMap) {
        for (String key : inputMap.keySet()) {
            this.put(key, inputMap.get(key)[0]);
        }
        return this;
    }

    public String toJson() {
        StringBuilder result = new StringBuilder("{\n");
        int size = this.keySet().size();
        int index = 0;

        for (String key : this.keySet()) {
            index++;
            String camelKey = toCamelCase(key);
            Object value = this.get(key);

            result.append("\t\"").append(camelKey).append("\" : ");

            if (value instanceof String) {
                result.append("\"").append(value).append("\"");
            } else if (value instanceof List) {
                List<?> list = (List<?>) value;
                result.append("[\n");
                for (int i = 0; i < list.size(); i++) {
                    Object item = list.get(i);
                    if (item instanceof BaseMap) {
                        result.append(((BaseMap) item).toJson().replaceAll("(?m)^", "\t\t")); // 들여쓰기 맞춤
                    } else {
                        result.append("\"").append(item.toString()).append("\"");
                    }
                    if (i < list.size() - 1) result.append(",");
                    result.append("\n");
                }
                result.append("\t]");
            } else if (value instanceof BaseMap) {
                result.append(((BaseMap) value).toJson().replaceAll("(?m)^", "\t\t"));
            } else {
                result.append("\"").append(value).append("\"");
            }

            if (index < size) result.append(",");
            result.append("\n");
        }

        result.append("}");
        return result.toString();
    }

    public static String toCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean nextUpperCase = false;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    result.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }

        return result.toString();
    }

    public BaseMap setUpdate(HttpServletRequest request) {
        return this.set("modfDt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .set("modfId", request.getSession().getAttribute("userId"))
            .set("createDt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .set("createId", request.getSession().getAttribute("userId"));
    }

    public BaseMap setOtMap(HttpServletRequest request) throws IOException {
        String body = new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        BaseMap map = new BaseMap();
        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                map.put(URLDecoder.decode(keyValue[0], "UTF-8"),
                        URLDecoder.decode(keyValue[1], "UTF-8"));
            }
        }
        return map;
    }
}
