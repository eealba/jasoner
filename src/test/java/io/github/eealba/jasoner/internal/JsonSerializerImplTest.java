package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.SerializationStrategy;
import io.github.eealba.jasoner.demo.model1.DemoPojo;
import io.github.eealba.jasoner.demo.model1.DemoPojo2;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;


class JsonSerializerImplTest {
    private static final String JSON_DEMO_POJO2_ALL = """
            {"name":"John","lastName":"Doe","age":30,"address":"New York", "address2":"New Jersey", "profession":"Developer"}""";

    private static final String JSON_ALL = """
            {"name":"John","lastName":"Doe","age":30,"address":"New York"}""";
    private static final String JSON_PACKAGE = """
            {"lastName":"Doe","age":30,"address":"New York"}""";
    private static final String JSON_PROTECTED = """
            {"lastName":"Doe","address":"New York"}""";
    private static final String JSON_PUBLIC = """
            {"address":"New York"}""";
    private static final String JSON_ALL_SNAKE_CASE = """
            {"name":"John","last_name":"Doe","age":30,"address":"New York"}""";

    private final DemoPojo demoPojo = DemoPojo.joeDoe();
    private final DemoPojo demoPojo2 = DemoPojo2.joeDoe2();
    private static final JsonSerializer privateJsonSerializer = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());
    private static final JsonSerializer privateSnakeCaseJsonSerializer = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.SNAKE_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());
    private static final JsonSerializer privatePrettyJsonSerializer = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(true)
            .build());
    private static final JsonSerializer protectedJsonSerializer = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PROTECTED)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    private static final JsonSerializer packageJsonSerializer = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PACKAGE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    private static final JsonSerializer publicJsonSerializer = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PUBLIC)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    @Test
    void should_serialize_object() {
        jsonAssertEquals(JSON_ALL, privateJsonSerializer.serialize(demoPojo));
    }
    @Test
    void should_serialize_demo_pojo2() {
        jsonAssertEquals(JSON_DEMO_POJO2_ALL, privateJsonSerializer.serialize(demoPojo2));
    }

    @Test
    void should_serialize_object_snake_case() {
        jsonAssertEquals(JSON_ALL_SNAKE_CASE, privateSnakeCaseJsonSerializer.serialize(demoPojo));
    }
    @Test
    void should_serialize_object_pretty() {
        jsonAssertEquals(JSON_ALL, privatePrettyJsonSerializer.serialize(demoPojo));
    }

    @Test
    void should_serialize_object_with_modifier_protected() {
        jsonAssertEquals(JSON_PROTECTED, protectedJsonSerializer.serialize(demoPojo));
    }

    @Test
    void should_serialize_object_with_modifier_package() {
        jsonAssertEquals(JSON_PACKAGE, packageJsonSerializer.serialize(demoPojo));
    }
    @Test
    void should_serialize_object_with_modifier_public()  {
        jsonAssertEquals(JSON_PUBLIC, publicJsonSerializer.serialize(demoPojo));
    }
    void jsonAssertEquals(String jsonExpected, String jsonActual) {
        try {
            System.out.println("Expected: " + jsonExpected);
            System.out.println("Actual: " + jsonActual);
            JSONAssert.assertEquals(jsonExpected, jsonActual, true);
        } catch (JSONException e) {
            throw new AssertionError(e);
        }
        
    }

}