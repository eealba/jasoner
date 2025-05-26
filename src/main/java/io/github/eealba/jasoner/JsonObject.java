package io.github.eealba.jasoner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface JsonObject {
    Set<String> keys();
    String getString(String key);
    Integer getInteger(String key);
    Boolean getBoolean(String key);
    Long getLong(String key);
    Double getDouble(String key);
    Float getFloat(String key);
    JsonObject getJsonObject(String key);
    List<?> getJsonArray(String key);

    Object get(String key);

    BigDecimal getBigDecimal(String key);
}
