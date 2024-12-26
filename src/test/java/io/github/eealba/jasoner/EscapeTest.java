package io.github.eealba.jasoner;

import io.github.eealba.example.PaymentInstruction;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class EscapeTest {
    static Jasoner jasoner;
    @BeforeAll
    static void setup() {
        jasoner = JasonerBuilder.create();
    }
    @Test
    void escape_quotes() throws IOException, JSONException {
        execute("/payment_instruction.json", PaymentInstruction.class);
    }

    private static void execute(String resourceControl, Class<?> clazz) throws IOException, JSONException {
        String control = readResource(resourceControl);
        var pojo = jasoner.fromJson(control, clazz);
        var json = jasoner.toJson(pojo);

        JSONAssert.assertEquals(control, json, true);
    }

    public static String readResource(String path) throws IOException {
        try (var inputStream = EscapeTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
