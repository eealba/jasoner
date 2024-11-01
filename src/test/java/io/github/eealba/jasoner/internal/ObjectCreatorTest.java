package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.demo.model2.CurrencyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObjectCreatorTest {

    @Test
    void should_create_instance_and_set_value() {
        var objectCreator = new ObjectCreator<>(io.github.eealba.jasoner.demo.model1.Plan.class);
        objectCreator.setValue("name", "Pepe");
        var plan = objectCreator.create();

        assertEquals("Pepe", plan.getName());
    }

    @Test
    void should_create_instance_and_set_value2() {
        var objectCreator = new ObjectCreator<>(io.github.eealba.jasoner.demo.model1.Plan.class);
        objectCreator.setValue("productId", "X123");
        var plan = objectCreator.create();

        assertEquals("X123", plan.getProductId());
    }

    @Test
    void should_create_instance_from_builder_and_set_value() {
        var objectCreator = new ObjectCreator<>(io.github.eealba.jasoner.demo.model2.Plan.class);
        objectCreator.setValue("name", "Master");
        var plan = objectCreator.create();

        assertEquals("Master", plan.name());
    }

    @Test
    void should_create_instance_from_builder_and_set_value2() {
        var objectCreator = new ObjectCreator<>(io.github.eealba.jasoner.demo.model2.Plan.class);
        objectCreator.setValue("name", "Master");
        var plan = objectCreator.create();

        assertEquals("Master", plan.name());
    }

    @Test
    void should_create_instance_of_record_class() {
        var objectCreator = new ObjectCreator<>(io.github.eealba.jasoner.demo.model2.Money.class);
        objectCreator.setValue("currencyCode", CurrencyCode.EUR);
        objectCreator.setValue("value", "10");
        var money = objectCreator.create();

        assertEquals(CurrencyCode.EUR,money.currencyCode());
        assertEquals("10",money.value());
    }

}