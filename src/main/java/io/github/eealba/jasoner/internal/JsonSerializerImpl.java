/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.JasonerProperty;
import io.github.eealba.jasoner.SerializationStrategy;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The class JsonSerializerImpl.
 * This class implements the JsonSerializer interface to serialize an object to a JSON string.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class JsonSerializerImpl implements JsonSerializer {
    private final JasonerConfig config;

    /**
     * Instantiates a new JsonSerializerImpl.
     *
     * @param config the configuration for the serializer
     */
    JsonSerializerImpl(JasonerConfig config) {
        this.config = Objects.requireNonNull(config);
    }

    /**
     * Serialize an object to a JSON string.
     *
     * @param writer the writer to write the JSON string to
     * @param obj the object to serialize
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void serialize(Writer writer, Object obj) throws IOException {

        Objects.requireNonNull(obj);
        var jsonWriter = new JsonWriter(Objects.requireNonNull(writer), config.pretty());
        if (obj instanceof List<?> list) {
            jsonArray(list, jsonWriter);
        } else {
            json(obj, jsonWriter);
        }
    }

    private void jsonArray(List<?> list, JsonWriter writer) throws IOException {
        writer.append(TokenImpl.ARRAY_START);
        var size = list.size();
        for (int i = 0; i < size; i++) {
            Object value = list.get(i);
            if (value instanceof String str) {
                writer.append(TokenImpl.createTextToken(str));
            } else if (value instanceof Number number) {
                writer.append(TokenImpl.createNumberToken(number.toString()));
            } else if (value instanceof Boolean bool) {
                writer.append(TokenImpl.createBooleanToken(bool.toString()));
            } else {
                json(value, writer);
            }
            if (i < size - 1) {
                writer.append(TokenImpl.COMMA);
            }
        }
        writer.append(TokenImpl.ARRAY_END);
    }

    private void json(Object entity, JsonWriter writer) throws IOException {
        writer.append(TokenImpl.OBJECT_START);
        var valueDataList = valueDataList(entity);
        serializeValueData(valueDataList, writer);
        writer.append(TokenImpl.OBJECT_END);
    }

    private ArrayList<ValueData> valueDataList(Object entity) {
        var valueDataList = new ArrayList<ValueData>();
        if (config.serializationStrategy() == SerializationStrategy.BOTH
                || config.serializationStrategy() == SerializationStrategy.METHOD) {
            valueDataList.addAll(Reflects.getGetterMethods(entity, config.modifierStrategy())
                    .stream()
                    .map((Method method) -> new ValueData(entity, method, null))
                    .toList());
        }
        if (config.serializationStrategy() == SerializationStrategy.BOTH
                || config.serializationStrategy() == SerializationStrategy.FIELD) {
            valueDataList.addAll(Reflects.getFields(entity, config.modifierStrategy())
                    .stream()
                    .map((Field field) -> new ValueData(entity, null, field))
                    .toList());
        }
        return valueDataList;
    }


    private void serializeValueData(List<ValueData> valueDataList, JsonWriter writer) throws IOException {
        for (int i = 0; i < valueDataList.size(); i++) {
            var valueData = valueDataList.get(i);
            var value = valueData.getValue();
            if (value != null) {
                var name = NamingFactory.get(config.namingStrategy()).apply(removePrefix(valueData.getName()));
                writer.append(TokenImpl.createTextToken(name));
                writer.append(TokenImpl.COLON);
                propertyValue(writer, value);
                if (i < valueDataList.size() - 1) {
                    writer.append(TokenImpl.COMMA);
                }
            }
        }
    }


    /**
     * Remove prefix from the given name.
     *
     * @param name the name to remove the prefix from
     * @return the name without the prefix
     */
    public String removePrefix(String name) {
        Objects.requireNonNull(name);
        if (config.removePrefixAccessors()) {
            String lowercase = name.toLowerCase();
            if (lowercase.startsWith("is")) {
                return name.substring(2);
            }
            if (lowercase.startsWith("has") || lowercase.startsWith("get")) {
                return name.substring(3);
            }
        }
        return name;
    }

    private void propertyValue(JsonWriter writer, Object value) throws IOException {
        //Arrays
        if (value.getClass().isArray()) {
            value = List.of((Object[]) value);
        }
        if (value instanceof Map<?,?> map) {
            jsonMap(map, writer);
            return;
        }
        if (value instanceof List<?> list) {
            jsonArray(list, writer);
            return;
        }
        //Simple types
        Optional<Token> tokenValue = getTokenValue(value);
        if (tokenValue.isPresent()) {
            writer.append(tokenValue.get());
            return;
        }
        //Objects
        json(value, writer);
    }

    private void jsonMap(Map<?,?> map, JsonWriter writer) throws IOException {
        writer.append(TokenImpl.OBJECT_START);
        var size = map.size();
        var i = 0;
        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            if (key instanceof String str) {
                writer.append(TokenImpl.createTextToken(str));
            } else {
                writer.append(TokenImpl.createTextToken(key.toString()));
            }
            writer.append(TokenImpl.COLON);

            Optional<Token> tokenValue = getTokenValue(value);
            if (tokenValue.isPresent()) {
                writer.append(tokenValue.get());

            }else{
                json(value, writer);
            }
            if (i < size - 1) {
                writer.append(TokenImpl.COMMA);
            }
            i++;
        }
        writer.append(TokenImpl.OBJECT_END);
    }

    private Optional<Token> getTokenValue(Object value) {
        Optional<Token> singleValue = getSingleValue(value);
        if (singleValue.isPresent()) {
            return singleValue;
        }
        if (config.unWrapSingleValueClasses()) {
            var valueDataList = valueDataList(value);
            if (valueDataList.size() == 1) {
                var newValue = valueDataList.get(0).getValue();
                return getSingleValue(newValue);
            }
        }
        return Optional.empty();
    }

    private static Optional<Token> getSingleValue(Object value) {
        if (value instanceof String str) {
            return Optional.of(TokenImpl.createTextToken(str));
        }
        if (value instanceof Enum<?> enu) {
            String tmp = enu.toString();
            if (tmp.equals("true")|| tmp.equals("false")) {
                return Optional.of(TokenImpl.createBooleanToken(tmp));
            }
            return Optional.of(TokenImpl.createTextToken(tmp));
        }
        if (value instanceof Number number){
            return Optional.of(TokenImpl.createNumberToken(number.toString()));
        }
        if (value instanceof Boolean bool) {
            return Optional.of(TokenImpl.createBooleanToken(bool.toString()));
        }
        if(!value.getClass().isArray() && classIgnoredForSerialization(value.getClass())){
            return Optional.of(TokenImpl.createTextToken(value.toString()));
        }

        return Optional.empty();
    }

    private static boolean classIgnoredForSerialization(Class<?> clazz) {
        return clazz.getName().startsWith("java");
    }

    /**
     * The type ValueData.
     * This class holds the value data for serialization.
     */
    static class ValueData {
        private final Method method;
        private final Field field;
        private final Object entity;

        private ValueData(Object entity, Method method, Field field) {
            this.method = method;
            this.field = field;
            this.entity = entity;
        }

        /**
         * Gets the value of the field or method.
         *
         * @return the value
         */
        Object getValue() {
            if (method != null) {
                return Reflects.invokeMethod(method, entity);
            }
            if (field != null) {
                return Reflects.getFieldValue(field, entity);
            }
            return null;
        }

        /**
         * Gets the name of the field or method.
         *
         * @return the name
         */
        String getName() {
            if (method != null) {
                var jasonerProperty = method.getAnnotation(JasonerProperty.class);
                return jasonerProperty != null ? jasonerProperty.value() : method.getName();
            }
            if (field != null) {
                var jasonerProperty = field.getAnnotation(JasonerProperty.class);
                return jasonerProperty != null ? jasonerProperty.value() : field.getName();
            }
            return null;
        }
    }
}