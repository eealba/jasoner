package io.github.eealba.jasoner.internal;

public interface Convert<Object, T> {
    T convert(Object obj);
}
