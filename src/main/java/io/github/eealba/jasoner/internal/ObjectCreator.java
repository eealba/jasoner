package io.github.eealba.jasoner.internal;


import io.github.eealba.jasoner.JasonerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class ObjectCreator<T> {
    private final Class<T> clazz;
    private final Object obj;
    ObjectCreator(Class<T> clazz) {
        this.clazz = clazz;
        if (clazz.isRecord() || Map.class.isAssignableFrom(clazz)){
            obj = new HashMap<String, Object>();
        }else {
            obj = Reflects.createBuilder(clazz).orElseGet(() -> Reflects.createObject(clazz).orElseThrow(
                    () -> new JasonerException("Cannot create instance of " + clazz.getName())));
        }
    }
    private Optional<Map<String, Object>> getMap() {
        if(obj instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) obj;
            return Optional.of(map);
        }
        return Optional.empty();
    }

    void setValue(String name, Object valueArg) {
        var parameterClass = getParameterClass(name);
        var value  = convertValue(parameterClass.orElseThrow(), valueArg);
        if(clazz.isRecord()) {
            getMap().ifPresent(m -> m.put(name, value));
        }else{
            var setter = Reflects.getSetterMethod(obj, name, value);
            if (setter.isPresent()){
                Reflects.invokeMethod(setter.get(), obj, new Object[]{value});
            }else {
                Reflects.getField(obj, name).ifPresent(f -> Reflects.setFieldValue(f, obj, value));
            }
        }
    }

    static Object convertValue(Class<?> parameterClass, Object valueArg) {
        if (parameterClass.isInstance(valueArg)){
            return valueArg;
        }
        return ConvertFactory.getConverter(parameterClass).convert(valueArg);
    }

    Optional<Class<?>> getParameterClass(String name) {
        if (clazz.isRecord()){
            return Reflects.getRecordParameterClass(clazz, name);
        }else{
            return Reflects.getSetterMethodParameterClass(obj, name)
                    .or(() -> Reflects.getFieldParameterClass(obj, name));
        }
    }


    T create() {
        if (clazz.isRecord()){
            return createRecord();
        }else{
            return createInstance();
        }
    }

    private T createRecord() {
        return Reflects.createRecord(clazz, getMap().orElseThrow()).orElseThrow();
    }

    private T createInstance() {
        if(obj.getClass().equals(clazz)) {
            return clazz.cast(obj);
        }
        return Reflects.createObjectFromBuilderInstance(obj, clazz)
                .orElseThrow(() -> new JasonerException("Cannot create instance of " + clazz.getName()));
    }

}
