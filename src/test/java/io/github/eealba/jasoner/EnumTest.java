package io.github.eealba.jasoner;

import io.github.eealba.example.ProcessorResponse;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class EnumTest {
    static Jasoner jasoner;
    @BeforeAll
    static void setup() {
        jasoner = JasonerBuilder.create();
    }
    @Test
    void order_serialize_deserialize() throws IOException, JSONException {
        execute("/processor_response.json", ProcessorResponse.class);
    }

    private static void execute(String resourceControl, Class<?> clazz) throws IOException, JSONException {
        String control = readResource(resourceControl);
        var pojo = jasoner.fromJson(control, clazz);
        var json = jasoner.toJson(pojo);

        JSONAssert.assertEquals(control, json, true);
    }

    public static String readResource(String path) throws IOException {
        try (var inputStream = EnumTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
