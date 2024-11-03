package io.github.eealba.jasoner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JasonerConfigTest {
    @Test
    void test_default_values_for_jasoner_config() {
        JasonerConfig config = new JasonerConfig.Builder().build();
        assertEquals(NamingStrategy.CAMEL_CASE, config.namingStrategy());
        assertEquals(ModifierStrategy.PUBLIC, config.modifierStrategy());
        assertTrue(config.removePrefixAccessors());
        assertEquals(SerializationStrategy.BOTH, config.serializationStrategy());
        assertFalse(config.pretty());
    }

}