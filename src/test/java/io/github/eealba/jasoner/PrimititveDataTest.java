package io.github.eealba.jasoner;

import io.github.eealba.example.PrimitiveData;
import org.junit.jupiter.api.Test;

public class PrimititveDataTest {
    @Test
    void testMaxPrimitiveData() {
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();

        // Create a new PrimitiveData object
        PrimitiveData data = PrimitiveData.maxValues();

        // Serialize the PrimitiveData object to a JSON string
        String json = jasoner.toJson(data);
        System.out.println(json);

        // Deserialize the JSON string back to a PrimitiveData object
        PrimitiveData deserializedData = jasoner.fromJson(json, PrimitiveData.class);
        System.out.println(deserializedData);
    }
    @Test
    void testMinPrimitiveData() {
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();

        // Create a new PrimitiveData object
        PrimitiveData data = PrimitiveData.minValues();

        // Serialize the PrimitiveData object to a JSON string
        String json = jasoner.toJson(data);
        System.out.println(json);

        // Deserialize the JSON string back to a PrimitiveData object
        PrimitiveData deserializedData = jasoner.fromJson(json, PrimitiveData.class);
        System.out.println(deserializedData);
    }

}
