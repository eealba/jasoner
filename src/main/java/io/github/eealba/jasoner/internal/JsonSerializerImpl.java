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
import io.github.eealba.jasoner.SerializationStrategy;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
/**
 * The type Json serializer.
 * This class is used to serialize an object to a JSON string.
 * @author Edgar Alba
 */
class JsonSerializerImpl implements JsonSerializer {
    private final JasonerConfig config;
    /**
     * Instantiates a new Json serializer.
     *
     * @param config the config
     */
    JsonSerializerImpl(JasonerConfig config) {
        this.config = config;
    }

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
        if (config.serializationStrategy() == SerializationStrategy.METHOD) {
            serializeWithMethods(entity, writer, indent);
        }
        appendNewLine(writer);
        indent(writer, indent);
        writer.append('}');
    }

    private void serializeWithMethods(Object entity, Writer writer, int indent) throws IOException {
        List<Method> methods = Reflects.getGetterMethods(entity, config.modifierStrategy());
        for(int i = 0; i < methods.size(); i++) {
            var method = methods.get(i);
            Object value = Reflects.invokeMethod( method, entity);
            if (value != null) {
                indent(writer, indent);
                propertyName(writer, method.getName());
                propertyValue(writer, value, indent);
                if (i < methods.size() - 1) {
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
     * Remove prefix string.
     *
     * @param name the name
     * @return the string
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
    void propertyValue(Writer writer, Object value, int indent) throws IOException {
        boolean quotes = needQuotes(value);
        if (quotes) {
            writer.append("\"");
        }
        if (value instanceof List<?> list) { // TODO procesar Arrays tb || value.getClass().isArray()
            writer.append("[");
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj != null) {
                    if (i > 0) {
                        writer.append(',');
                        appendNewLine(writer);
                    }
                    propertyValue(writer, obj, indent);
                }
            }
            writer.append(']');

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



    private boolean classIgnoredForSerialization(Class<?> clase) {
        return clase.getName().startsWith("java");
    }


}
