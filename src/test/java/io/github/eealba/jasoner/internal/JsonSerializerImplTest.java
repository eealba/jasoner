package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.demo.model1.DemoPojo;
import io.github.eealba.jasoner.demo.model1.DemoPojo2;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import static io.github.eealba.jasoner.internal.Helper.PACKAGE_JSON_SERIALIZER;
import static io.github.eealba.jasoner.internal.Helper.PRIVATE_JSON_SERIALIZER;
import static io.github.eealba.jasoner.internal.Helper.PRIVATE_PRETTY_JSON_SERIALIZER;
import static io.github.eealba.jasoner.internal.Helper.PRIVATE_SNAKE_CASE_JSON_SERIALIZER;
import static io.github.eealba.jasoner.internal.Helper.PROTECTED_JSON_SERIALIZER;
import static io.github.eealba.jasoner.internal.Helper.PUBLIC_JSON_SERIALIZER;


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

    @Test
    void should_serialize_object() {
        jsonAssertEquals(JSON_ALL, PRIVATE_JSON_SERIALIZER.serialize(demoPojo));
    }
    @Test
    void should_serialize_demo_pojo2() {
        jsonAssertEquals(JSON_DEMO_POJO2_ALL, PRIVATE_JSON_SERIALIZER.serialize(demoPojo2));
    }

    @Test
    void should_serialize_object_snake_case() {
        jsonAssertEquals(JSON_ALL_SNAKE_CASE, PRIVATE_SNAKE_CASE_JSON_SERIALIZER.serialize(demoPojo));
    }
    @Test
    void should_serialize_object_pretty() {
        jsonAssertEquals(JSON_ALL, PRIVATE_PRETTY_JSON_SERIALIZER.serialize(demoPojo));
    }

    @Test
    void should_serialize_object_with_modifier_protected() {
        jsonAssertEquals(JSON_PROTECTED, PROTECTED_JSON_SERIALIZER.serialize(demoPojo));
    }

    @Test
    void should_serialize_object_with_modifier_package() {
        jsonAssertEquals(JSON_PACKAGE, PACKAGE_JSON_SERIALIZER.serialize(demoPojo));
    }
    @Test
    void should_serialize_object_with_modifier_public()  {
        jsonAssertEquals(JSON_PUBLIC, PUBLIC_JSON_SERIALIZER.serialize(demoPojo));
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