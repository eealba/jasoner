package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonException;

import java.lang.reflect.InvocationTargetException;

class ConvertSimpleRecord<T> implements Convert<Object, T> {
    private final Class<T> type;
    public ConvertSimpleRecord(Class<T> type) {
        if (type.getDeclaredConstructors()[0].getParameters().length != 1){
            throw new JsonException("Record class must have only one parameter in the constructor");
        }
        this.type = type;
    }

    @Override
    public T convert(Object obj) {
        try {
            return type.cast(type.getDeclaredConstructors()[0].newInstance(obj));
        } catch (IllegalArgumentException | InstantiationException | IllegalAccessException
                 | InvocationTargetException e) {
            throw new JsonException(e);
        }
    }
}
