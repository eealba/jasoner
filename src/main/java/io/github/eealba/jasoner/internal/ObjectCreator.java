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

import io.github.eealba.jasoner.JasonerException;
import io.github.eealba.jasoner.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The class ObjectCreator.
 * This class is responsible for creating instances of objects, including records and maps.
 *
 * @param <T> the type of the object to create
 * @author Edgar Alba
 * @version 1.0
 * @since 1.0
 */
class ObjectCreator<T> {
    private final Class<T> clazz;
    private final Class<?> clazzComponentType;
    private final Object obj;

    /**
     * Instantiates a new ObjectCreator.
     *
     * @param clazz the class of the object to create
     */
    ObjectCreator(Class<T> clazz) {
        this.clazz = clazz;
        this.clazzComponentType = clazz.isArray() ? clazz.getComponentType() : null;
        if (clazz.isRecord() || Map.class.isAssignableFrom(clazz) || JsonObject.class.isAssignableFrom(clazz)) {
            obj = new HashMap<String, Object>();
        } else if (clazz.isArray()) {
            obj = Reflects.createBuilder(clazzComponentType).orElseGet(() -> Reflects.createJsonObject(clazzComponentType).orElseThrow(
                    () -> new JasonerException("Cannot create instance of " + clazz.getName())));
        } else {
            obj = Reflects.createBuilder(clazz).orElseGet(() -> Reflects.createJsonObject(clazz).orElseThrow(
                    () -> new JasonerException("Cannot create instance of " + clazz.getName())));
        }
    }

    boolean isMap() {
        return obj instanceof Map;
    }

    private Optional<Map<String, Object>> getMap() {
        if (obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            return Optional.of(map);
        }
        return Optional.empty();
    }

    /**
     * Sets the value of a property.
     *
     * @param name     the name of the property
     * @param valueArg the value to set
     */
    void setValue(String name, Object valueArg) {
        var parameterClass = getParameterClass(name);
        Object value;
        if (JsonObject.class.isAssignableFrom(clazz) && valueArg instanceof Map<?, ?>) {
            value = Reflects.createJsonObject(clazz, valueArg).orElseThrow(() -> new JasonerException("Cannot create instance of " + clazz.getName()));
        }else if (parameterClass.isPresent()) {
            value = convertValue(parameterClass.get(), valueArg);
        } else {
            value = valueArg;
        }
        if (isMap()) {
            getMap().orElseThrow().put(name, value);
        } else {
            var setter = Reflects.getSetterMethod(obj, name, value);
            if (setter.isPresent()) {
                Reflects.invokeMethod(setter.get(), obj, new Object[]{value});
            } else {
                Reflects.getField(obj, name).ifPresent(f -> Reflects.setFieldValue(f, obj, value));
            }
        }
    }

    /**
     * Converts a value to the specified type.
     *
     * @param parameterClass the class of the parameter
     * @param valueArg       the value to convert
     * @return the converted value
     */
    static Object convertValue(Class<?> parameterClass, Object valueArg) {
        if (parameterClass.isInstance(valueArg)) {
            return valueArg;
        }
        if (valueArg instanceof List<?> list) {
            return list.stream().map(o -> convertValue(parameterClass, o)).toList();
        }
        return ConvertFactory.getConverter(parameterClass).convert(valueArg);
    }

    /**
     * Gets the parameter class of a property.
     *
     * @param name the name of the property
     * @return an optional containing the parameter class, or empty if not found
     */
    Optional<Class<?>> getParameterClass(String name) {
        if (JsonObject.class.isAssignableFrom(clazz)) {
            return Optional.of(clazz);
        }else if (clazz.isRecord()) {
            return Reflects.getRecordParameterClass(clazz, name);
        } else {
            return Reflects.getSetterMethodParameterClass(obj, name)
                           .or(() -> Reflects.getFieldParameterClass(obj, name));
        }
    }

    /**
     * Creates an instance of the object.
     *
     * @return the created object
     */
    T create() {
        if (clazz.isRecord()) {
            return createRecord();
        } else {
            return createInstance();
        }
    }

    private T createRecord() {
        return Reflects.createRecord(clazz, getMap().orElseThrow()).orElseThrow();
    }

    @SuppressWarnings("unchecked")
    private T createInstance() {
        if (JsonObject.class.isAssignableFrom(clazz)) {
            return (T) Reflects.createJsonObject(clazz, obj)
                               .orElseThrow(() -> new JasonerException("Cannot create instance of " + clazz.getName()));
        }
        if (clazz.isArray()) {
            return (T) Reflects.createObjectFromBuilderInstance(obj, clazzComponentType)
                               .orElseThrow(() -> new JasonerException("Cannot create instance of " + clazz.getName()));
        }
        if (obj.getClass().equals(clazz) || clazz.isInstance(obj)) {
            return clazz.cast(obj);
        }
        return Reflects.createObjectFromBuilderInstance(obj, clazz)
                       .orElseThrow(() -> new JasonerException("Cannot create instance of " + clazz.getName()));
    }
}