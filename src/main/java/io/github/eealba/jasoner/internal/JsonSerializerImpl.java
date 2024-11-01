package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonSerializer;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.PrefixAccessorStrategy;
import io.github.eealba.jasoner.SerializationStrategy;

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
    public String serialize(Object obj) {
        Objects.requireNonNull(obj);
        var buff = new StringBuilder(1000);
        if (obj instanceof List<?> list) {
            jsonArray(list, buff);
        } else {
            json(obj, buff, 0);
        }
        return buff.toString();
    }

    private void jsonArray(List<?> list, StringBuilder buff) {
        buff.append('[');
        list.forEach(ele -> {
            appendNewLine(buff);
            json(ele, buff, 0);
            buff.append(',');
        });
        buff.setLength(buff.length() - 1); // delete las (,)
        appendNewLine(buff);
        buff.append(']');
    }

    private void json(Object entity, StringBuilder buff, int indent) {
        buff.append('{');
        appendNewLine(buff);
        if (serializationStrategy == SerializationStrategy.METHOD) {
            serializeWithMethods(entity, buff, indent);
        }
        appendNewLine(buff);
        indent(buff, indent).append('}');
    }

    private void serializeWithMethods(Object entity, StringBuilder buff, int indent) {
        List<Method> methods = Reflects.getGetterMethods(entity, modifierStrategy);
        for(int i = 0; i < methods.size(); i++) {
            var method = methods.get(i);
            Object value = Reflects.invokeMethod( method, entity);
            if (value != null) {
                indent(buff, indent);
                propertyName(buff, method.getName());
                propertyValue(buff, value, indent);
                if (i < methods.size() - 1) {
                    buff.append(',');
                }
                appendNewLine(buff);

            }
        }
    }

    private StringBuilder indent(StringBuilder buff, int indent) {
        if (pretty) {
            while (indent > 0) {
                buff.append("  ");
                --indent;
            }
        }
        return buff;
    }


    private void propertyName(StringBuilder buff, String name) {
        buff.append("\"");
        buff.append(naming.apply(prefixAccessorStrategy.removePrefix(name)));
        buff.append("\": ");

    }

    void propertyValue(StringBuilder buff, Object value, int indent) {
        boolean quotes = needQuotes(value);
        if (quotes) {
            buff.append("\"");
        }
        if (value instanceof List<?> list) { // TODO procesar Arrays tb || value.getClass().isArray()
            buff.append("[");
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj != null) {
                    if (i > 0) {
                        buff.append(',');
                        appendNewLine(buff);
                    }
                    propertyValue(buff, obj, indent);
                }
            }
            buff.append(']');

        } else if (value.getClass().isEnum() || classIgnoredForSerialization(value.getClass())) {
            String _value = value.toString();
            buff.append(_value);
        } else {
            json(value, buff, indent);
        }
        if (quotes) {
            buff.append("\"");
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

    private void appendNewLine(StringBuilder buff) {
        if (pretty) {
            buff.append('\n');
        }
    }



    private boolean classIgnoredForSerialization(Class<?> clase) {
        return clase.getName().startsWith("java");
    }


}
