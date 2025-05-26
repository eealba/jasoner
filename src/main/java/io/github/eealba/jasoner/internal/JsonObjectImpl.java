package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

class JsonObjectImpl implements JsonObject {
    private final java.util.Map<String, Object> map;

    JsonObjectImpl(java.util.Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public java.util.Set<String> keys() {
        return map.keySet();
    }

    @Override
    public String getString(String key) {
        return (String) get(key);
    }

    @Override
    public Integer getInteger(String key) {
        return (Integer) get(key);
    }

    @Override
    public Boolean getBoolean(String key) {
        return (Boolean) get(key);
    }

    @Override
    public Long getLong(String key) {
        return (Long) get(key);
    }

    @Override
    public Double getDouble(String key) {
        return (Double) get(key);
    }

    @Override
    public Float getFloat(String key) {
        return (Float) get(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return (BigDecimal) get(key);
    }

    @Override
    public JsonObject getJsonObject(String key) {
        return (JsonObject) get(key);
    }

    @Override
    public List<?> getJsonArray(String key) {
        return (List<?>) get(key);
    }

    @Override
    public Object get(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        Object value = map.get(key);
        if (value != null) {
            return value;
        }
        // parse the key by dot for nested objects and arrays
        String[] keys = key.split("\\.");
        Object current = map;
        for (String k : keys) {
            if (current instanceof Map map2) {
                current = map2.get(k);
            }else  if (current instanceof JsonObject jsonObject) {
                current = jsonObject.get(k);
            }else if (current instanceof List) {
                try {
                    int index = Integer.parseInt(k);
                    current = ((List<?>) current).get(index);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid index: " + k, e);
                }
            }
        }
        if (current == map){
            throw new IllegalArgumentException("Key not found: " + key);
        }

        return current;
    }


}
