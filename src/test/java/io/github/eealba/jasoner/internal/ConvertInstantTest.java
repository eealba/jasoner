package io.github.eealba.jasoner.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertInstantTest {

    @Test
    void convert() {
        var res = new ConvertInstant().convert("2021-08-01T00:00:00Z");
        assertEquals("2021-08-01T00:00:00Z", res.toString());
    }
}