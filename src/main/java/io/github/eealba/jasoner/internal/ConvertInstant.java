package io.github.eealba.jasoner.internal;

import java.time.Instant;

public class ConvertInstant implements Convert<Object, Instant> {

    @Override
    public Instant convert(Object obj) {
        return Instant.parse(obj.toString());
    }
}
