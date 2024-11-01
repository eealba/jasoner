package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.PrefixAccessorStrategy;
import io.github.eealba.jasoner.SerializationStrategy;
import io.github.eealba.jasoner.demo.model1.DemoPojo;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;


class JsonSerializerImplTest {
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

    private final DemoPojo demoPojo = DemoPojo.JoeDoe();

    private static final JsonSerializer privateJsonSerializer = new JsonSerializerBuilderImpl()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .prefixAccessorStrategy(PrefixAccessorStrategy.REMOVE)
            .pretty(false)
            .build();
    private static final JsonSerializer privateSnakeCaseJsonSerializer = new JsonSerializerBuilderImpl()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.SNAKE_CASE)
            .prefixAccessorStrategy(PrefixAccessorStrategy.REMOVE)
            .pretty(false)
            .build();
    private static final JsonSerializer privatePrettyJsonSerializer = new JsonSerializerBuilderImpl()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .prefixAccessorStrategy(PrefixAccessorStrategy.REMOVE)
            .pretty(true)
            .build();
    private static final JsonSerializer protectedJsonSerializer = new JsonSerializerBuilderImpl()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PROTECTED)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .prefixAccessorStrategy(PrefixAccessorStrategy.REMOVE)
            .pretty(false)
            .build();

    private static final JsonSerializer packageJsonSerializer = new JsonSerializerBuilderImpl()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PACKAGE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .prefixAccessorStrategy(PrefixAccessorStrategy.REMOVE)
            .pretty(false)
            .build();

    private static final JsonSerializer publicJsonSerializer = new JsonSerializerBuilderImpl()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PUBLIC)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .prefixAccessorStrategy(PrefixAccessorStrategy.REMOVE)
            .pretty(false)
            .build();

    @Test
    void should_serialize_object() throws JSONException {
        JSONAssert.assertEquals(JSON_ALL, privateJsonSerializer.serialize(demoPojo), true);
    }
    @Test
    void should_serialize_object_snake_case() throws JSONException {
        JSONAssert.assertEquals(JSON_ALL_SNAKE_CASE, privateSnakeCaseJsonSerializer.serialize(demoPojo), true);
    }
    @Test
    void should_serialize_object_pretty() throws JSONException {
        JSONAssert.assertEquals(JSON_ALL, privatePrettyJsonSerializer.serialize(demoPojo), true);
    }

    @Test
    void should_serialize_object_with_modifier_protected() throws JSONException {
        JSONAssert.assertEquals(JSON_PROTECTED, protectedJsonSerializer.serialize(demoPojo), true);
    }

    @Test
    void should_serialize_object_with_modifier_package() throws JSONException {
        JSONAssert.assertEquals(JSON_PACKAGE, packageJsonSerializer.serialize(demoPojo), true);
    }
    @Test
    void should_serialize_object_with_modifier_public() throws JSONException {
        JSONAssert.assertEquals(JSON_PUBLIC, publicJsonSerializer.serialize(demoPojo), true);
    }

}