package io.github.eealba.jasoner.internal;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonTokenizerImplTest {

    @Test
    void tokens() {
        JsonTokenizerImpl jsonTokenizer = new JsonTokenizerImpl("{\"name\":\"John\"}");
        List<Token> tokens = jsonTokenizer.tokens();
        assertEquals(5, tokens.size());
        assertEquals("{", tokens.get(0).stringValue());
        assertEquals("name", tokens.get(1).stringValue());
        assertEquals(":", tokens.get(2).stringValue());
        assertEquals("John", tokens.get(3).stringValue());
        assertEquals("}", tokens.get(4).stringValue());
    }
}