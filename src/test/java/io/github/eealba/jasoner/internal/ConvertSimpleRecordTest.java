package io.github.eealba.jasoner.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertSimpleRecordTest {

    @Test
    void convert() {
        var res = new ConvertSimpleRecord<>(ValueObject.class).convert("dummy");
        assertEquals("dummy",res.value());
    }
    record ValueObject(String value) {
    }
}