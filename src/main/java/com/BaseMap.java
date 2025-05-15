package com;

import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

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
        String result = "{\n";
        int size = this.keySet().size();
        int index = 0;

        for (String key : this.keySet()) {
            index++;
            result += "\t" + "\"" + toCamelCase(key) + "\" : \"" + this.getString(key).toString() + "\"";
            if (index < size) {
                result += ",";
            }
            result += "\n";
        }

        result += "}";
        return result;
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
}
