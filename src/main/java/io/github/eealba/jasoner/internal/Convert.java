package io.github.eealba.jasoner.internal;

interface Convert<Object, T> {
    T convert(Object obj);
}
