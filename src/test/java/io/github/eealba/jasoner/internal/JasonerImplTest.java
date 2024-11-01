package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.demo.model1.DemoPojo;
import io.github.eealba.jasoner.demo.model1.DemoPojo2;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static io.github.eealba.jasoner.internal.Helper.PRIVATE_JASONER;
import static org.junit.jupiter.api.Assertions.*;

class JasonerImplTest {

    @Test
    void should_serialize_and_deserialize() {
        var demoPojo = DemoPojo.joeDoe();
        var json = PRIVATE_JASONER.toJson(demoPojo);

        assertNotNull(json);

        var obj = PRIVATE_JASONER.fromJson(json, DemoPojo.class);

        assertNotNull(obj);
        assertTrue(Objects.deepEquals(demoPojo, obj));
    }
    @Test
    void should_serialize_and_deserialize_2() {
        var demoPojo2 = DemoPojo2.joeDoe2();
        var json = PRIVATE_JASONER.toJson(demoPojo2);

        assertNotNull(json);

        var obj = PRIVATE_JASONER.fromJson(json, DemoPojo2.class);

        assertNotNull(obj);
        assertTrue(Objects.deepEquals(demoPojo2, obj));
    }

}