package ru.open.swagger.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePojo {

    protected Map<String, Object> jsonBody = new HashMap<>();

    public Map<String, Object> getJsonBody() {
        return jsonBody;
    }

    public BasePojo withProp(String prop, Map<String, String> body) {
        jsonBody.put(prop, body);
        return this;
    }

    public BasePojo withProp(String prop, String value) {
        jsonBody.put(prop, value);
        return this;
    }

    public BasePojo withProp(String prop, Integer value) {
        jsonBody.put(prop, value);
        return this;
    }

    public BasePojo withProp(String prop, Boolean value) {
        jsonBody.put(prop, value);
        return this;
    }

    public BasePojo withProp(String prop, List<Object> value) {
        jsonBody.put(prop, value);
        return this;
    }

    public BasePojo withProp(String prop, ArrayList<Map<String, Object>> value) {
        jsonBody.put(prop, value);
        return this;
    }

    public BasePojo withOut(String prop) {
        jsonBody.remove(prop);
        return this;
    }
}
