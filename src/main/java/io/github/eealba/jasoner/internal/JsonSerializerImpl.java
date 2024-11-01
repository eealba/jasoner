package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.PrefixAccessorStrategy;
import io.github.eealba.jasoner.SerializationStrategy;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

class JsonSerializerImpl implements JsonSerializer {
    private final Naming naming;
    private final SerializationStrategy serializationStrategy;
    private final ModifierStrategy modifierStrategy;
    private final PrefixAccessorStrategy prefixAccessorStrategy;
    private final boolean pretty;

    JsonSerializerImpl(JsonSerializerBuilderImpl builder) {
        naming = NamingFactory.get(builder.ns);
        serializationStrategy = builder.ss;
        modifierStrategy = builder.ms;
        prefixAccessorStrategy = builder.pas;
        pretty = builder.pretty;
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
        if (serializationStrategy == SerializationStrategy.METHOD) {
            serializeWithMethods(entity, writer, indent);
        }
        appendNewLine(writer);
        indent(writer, indent);
        writer.append('}');
    }

    private void serializeWithMethods(Object entity, Writer writer, int indent) throws IOException {
        List<Method> methods = Reflects.getGetterMethods(entity, modifierStrategy);
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
        if (pretty) {
            while (indent > 0) {
                writer.append("  ");
                --indent;
            }
        }
    }


    private void propertyName(Writer writer, String name) throws IOException {
        writer.append("\"");
        writer.append(naming.apply(prefixAccessorStrategy.removePrefix(name)));
        writer.append("\": ");

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
        if (pretty) {
            writer.append('\n');
        }
    }



    private boolean classIgnoredForSerialization(Class<?> clase) {
        return clase.getName().startsWith("java");
    }


}
