package io.github.eealba.jasoner;

import io.github.eealba.example.DateTimeData;
import org.junit.jupiter.api.Test;

public class DateTimeDataTest {
    @Test
    void testFirstJanuary() {
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();

        // Create a new DateTimeData object
        DateTimeData data = DateTimeData.firstJanuary();

        // Serialize the DateTimeData object to a JSON string
        String json = jasoner.toJson(data);
        System.out.println(json);

        // Deserialize the JSON string back to a DateTimeData object
        DateTimeData deserializedData = jasoner.fromJson(json, DateTimeData.class);
        System.out.println(deserializedData);
    }
}
