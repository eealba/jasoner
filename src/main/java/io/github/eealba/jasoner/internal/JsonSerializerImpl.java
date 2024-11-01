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

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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
        Objects.requireNonNull(writer);
        Objects.requireNonNull(obj);
        if (obj instanceof List<?> list) {
            jsonArray(list, writer);
        } else {
            json(obj, writer, 0);
        }
    }

    private void jsonArray(List<?> list, Writer writer) throws IOException {
        writer.append('[');
        var size = list.size();
        for (int i = 0; i < size; i++) {
            appendNewLine(writer);
            json(list.get(i), writer, 0);
            if (i < size - 1) {
                writer.append(',');
            }
        }
        appendNewLine(writer);
        writer.append(']');
    }

    private void json(Object entity, Writer writer, int indent) throws IOException {
        writer.append('{');
        appendNewLine(writer);

        var valueDataList = new java.util.ArrayList<>(Reflects.getGetterMethods(entity, config.modifierStrategy())
                .stream()
                .map((Method method) -> new ValueData(entity, method, null))
                .toList());

        var fields = Reflects.getFields(entity, config.modifierStrategy())
                .stream()
                .filter(fieldPredicate(valueDataList))
                .map((Field field) -> new ValueData(entity, null, field))
                .toList();

        valueDataList.addAll(fields);

        serializeValueData(valueDataList, writer, indent);

        appendNewLine(writer);
        indent(writer, indent);
        writer.append('}');
    }

    private Predicate<Field> fieldPredicate(List<ValueData> valueDataList) {
        return (Field f) -> {
            boolean found = false;
            for (ValueData valueData : valueDataList) {
                if (removePrefix(valueData.getName()).equals(f.getName())) {
                    found = true;
                    break;
                }
            }
            return !found;
        };
    }

    private void serializeValueData(List<ValueData> valueDataList, Writer writer, int indent) throws IOException {
        for (int i = 0; i < valueDataList.size(); i++) {
            var valueData = valueDataList.get(i);
            var value = valueData.getValue();
            if (value != null) {
                indent(writer, indent);
                propertyName(writer, valueData.getName());
                propertyValue(writer, value, indent);
                if (i < valueDataList.size() - 1) {
                    writer.append(',');
                }
                appendNewLine(writer);
            }
        }
    }

    private void indent(Writer writer, int indent) throws IOException {
        if (config.pretty()) {
            while (indent > 0) {
                writer.append("  ");
                --indent;
            }
        }
    }

    private void propertyName(Writer writer, String name) throws IOException {
        writer.append("\"");
        writer.append(NamingFactory.get(config.namingStrategy()).apply(removePrefix(name)));
        writer.append("\": ");
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

    private void propertyValue(Writer writer, Object value, int indent) throws IOException {
        boolean quotes = needQuotes(value);
        if (quotes) {
            writer.append("\"");
        }
        if (value.getClass().isArray()) {
            value = List.of((Object[]) value);
        }
        if (value instanceof List<?> list) {
            jsonArray(list, writer);
        } else if (value.getClass().isEnum() || classIgnoredForSerialization(value.getClass())) {
            String _value = value.toString();
            writer.append(_value);
        } else {
            json(value, writer, indent);
        }
        if (quotes) {
            writer.append("\"");
        }
    }

    private boolean needQuotes(Object value) {
        boolean com = true;
        if (value instanceof Enum<?> enu) {
            String tmp = enu.toString();
            if (tmp.equals("true") || tmp.equals("false")) {
                com = false;
            }
        } else if (value instanceof Number || value instanceof Boolean || value instanceof List
                || value.getClass().isArray() || !classIgnoredForSerialization(value.getClass())) {
            com = false;
        }
        return com;
    }

    private void appendNewLine(Writer writer) throws IOException {
        if (config.pretty()) {
            writer.append('\n');
        }
    }

    private boolean classIgnoredForSerialization(Class<?> clazz) {
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
                return method.getName();
            }
            if (field != null) {
                return field.getName();
            }
            return null;
        }
    }
}