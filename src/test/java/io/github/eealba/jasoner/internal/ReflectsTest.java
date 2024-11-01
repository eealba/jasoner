package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerException;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.demo.model1.DemoPojo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
class ReflectsTest {


    @Test
    void should_return_all_methods() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var methods = Reflects.getMethods(plan);

        assertTrue(methods.size() > 10);
    }
    @Test
    void should_filter__methods() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var allMethods = Reflects.getMethods(plan);
        var methods = Reflects.getMethods(plan, (Method m) -> m.getName().contains("name"));

        assertTrue(methods.size() < allMethods.size());
    }

    @Test
    void should_return_all_Fields() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var fields = Reflects.getFields(plan);

        assertEquals(7, fields.size());
    }
    @Test
    void should_return_Field() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var field = Reflects.getField(plan, "name");

        assertTrue(field.isPresent());
    }
    @Test
    void should_set_Field_value() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var field = Reflects.getField(plan, "name");
        field.ifPresent(value -> Reflects.setFieldValue(value, plan, "Pepe"));

        assertEquals("Pepe", plan.getName());
    }

    @Test
    void should_not_create_instance_because_private_default_constructor() {
        var result = Reflects.createObject(io.github.eealba.jasoner.demo.model2.Plan.class);
       assertFalse(result.isPresent());
    }
    @Test
    void should_create_instance_because_public_default_constructor() {
        var result = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class);
        assertTrue(result.isPresent());
    }

    @Test
    void should_create_instanceof_builder() {
        var planBuilder = Reflects.createBuilder(io.github.eealba.jasoner.demo.model2.Plan.class);

        assertTrue(planBuilder.isPresent());
        assertEquals(planBuilder.orElseThrow().getClass(), io.github.eealba.jasoner.demo.model2.Plan.Builder.class);
    }

    @Test
    void should_create_instanceof_class_from_builder() {
        var planBuilder = Reflects.createBuilder(io.github.eealba.jasoner.demo.model2.Plan.class);

        assertTrue(planBuilder.isPresent());

        var plan = Reflects.createObjectFromBuilderInstance(planBuilder.orElseThrow(),
                io.github.eealba.jasoner.demo.model2.Plan.class);

        assertTrue(plan.isPresent());
        assertEquals(plan.orElseThrow().getClass(), io.github.eealba.jasoner.demo.model2.Plan.class);
    }
    @Test
    void should_create_instanceof_class_from_builder_with_default_values() {
        var planBuilder = Reflects.createBuilder(io.github.eealba.jasoner.demo.model2.Plan.class);

        assertTrue(planBuilder.isPresent());

        var plan = Reflects.createObjectFromBuilderInstance(planBuilder.orElseThrow(),
                io.github.eealba.jasoner.demo.model2.Plan.class);

        assertTrue(plan.isPresent());
        assertEquals(plan.orElseThrow().getClass(), io.github.eealba.jasoner.demo.model2.Plan.class);
    }

    @Test
    void should_get_setter_method() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        findSetterAndVerify(plan, "name", "Hola", "setName");
        findSetterAndVerify(plan, "productId", "123", "setProductId");
        findSetterAndVerify(plan, "product_Id", "123", "setProductId");
        findSetterAndVerify(plan, "product_id", "123", "setProductId");
        findSetterAndVerify(plan, "ProductId", "123", "setProductId");
        findSetterAndVerify(plan, "PRODUCT_ID", "123", "setProductId");
    }
    @Test
    void should_get_setter_method2() {
        var planBuilder = Reflects.createBuilder(io.github.eealba.jasoner.demo.model2.Plan.class).orElseThrow();
        findSetterAndVerify(planBuilder, "name", "Hola", "name");
        findSetterAndVerify(planBuilder, "productId", "123", "productId");
        findSetterAndVerify(planBuilder, "product_Id", "123", "productId");
        findSetterAndVerify(planBuilder, "product_id", "123", "productId");
        findSetterAndVerify(planBuilder, "ProductId", "123", "productId");
        findSetterAndVerify(planBuilder, "PRODUCT_ID", "123", "productId");
    }

    @Test
    void should_return_parameter_class() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var clazz = Reflects.getSetterMethodParameterClass(plan, "payment_preferences");

        assertEquals(io.github.eealba.jasoner.demo.model1.PaymentPreferences.class, clazz.orElseThrow());
    }

    @Test
    void should_return_parameter_class2() {
        var plan = Reflects.createObject(io.github.eealba.jasoner.demo.model1.Plan.class).orElseThrow();
        var clazz = Reflects.getSetterMethodParameterClass(plan, "billing_cycles");

        assertEquals(io.github.eealba.jasoner.demo.model1.BillingCycle.class, clazz.orElseThrow());
    }

    private static void findSetterAndVerify(Object instance, String name, Object value, String expectedName) {
        var method = Reflects.getSetterMethod(instance, name, value);

        assertFalse(method.isEmpty());
        assertEquals(expectedName, method.get().getName());
    }
    @Test
    void should_create_instance_of_record_class() {
        var map = new HashMap<String, Object>();
        map.put("currencyCode", io.github.eealba.jasoner.demo.model2.CurrencyCode.EUR);
        map.put("value", "10");

        var money = Reflects.createRecord(io.github.eealba.jasoner.demo.model2.Money.class, map)
                .orElseThrow();

        assertEquals(io.github.eealba.jasoner.demo.model2.CurrencyCode.EUR, money.currencyCode());
        assertEquals("10",money.value());
    }
    @Test
    void should_return_record_parameter_class() {
        var parameterClass = Reflects.getRecordParameterClass(
                io.github.eealba.jasoner.demo.model2.Money.class, "currencyCode");
        assertEquals(io.github.eealba.jasoner.demo.model2.CurrencyCode.class,
                parameterClass.orElseThrow());
    }

    @Test
    void invoke_method_with_arguments() throws InvocationTargetException, IllegalAccessException {
        var method = mock(Method.class);
        when(method.trySetAccessible()).thenReturn(true);
        when(method.invoke(any(), any())).thenReturn("Hola");

        var result = Reflects.invokeMethod(method, "dummy", new Object[]{"Pepe"});

        assertEquals("Hola", result);
    }
    @Test
    void fail_invoke_method_with_arguments() throws InvocationTargetException, IllegalAccessException {
        var method = mock(Method.class);
        when(method.getName()).thenReturn("name");
        when(method.trySetAccessible()).thenReturn(false);
        when(method.invoke(any(), any())).thenReturn("Hola");

        var res = assertThrows(JasonerException.class, () -> Reflects.invokeMethod(method, "dummy",
                new Object[]{"Pepe"}));

        assertEquals("The method 'name' is not accessible", res.getMessage());
        verify(method, never()).invoke(any(), any());
    }
    @Test
    void invoke_method_without_arguments() throws InvocationTargetException, IllegalAccessException {
        var method = mock(Method.class);
        when(method.trySetAccessible()).thenReturn(true);
        when(method.invoke(any(),any())).thenReturn("Hola");

        var result = Reflects.invokeMethod(method, "dummy");
        assertEquals("Hola", result);
    }

    @Test
    void fail_invoke_method_without_arguments() throws InvocationTargetException, IllegalAccessException {
        var method = mock(Method.class);
        when(method.getName()).thenReturn("name");
        when(method.trySetAccessible()).thenReturn(false);
        when(method.invoke(any())).thenReturn("Hola");

        var res = assertThrows(JasonerException.class, () -> Reflects.invokeMethod(method, "dummy"));

        assertEquals("The method 'name' is not accessible", res.getMessage());
        verify(method, never()).invoke(any());

    }

    @Test
    void should_return_public_getters() {
        assertEquals(1, Reflects.getGetterMethods( new DemoPojo(), ModifierStrategy.PUBLIC).size());
    }
    @Test
    void should_return_public_protected_getters() {
        assertEquals(2, Reflects.getGetterMethods( new DemoPojo(), ModifierStrategy.PROTECTED).size());
    }
    @Test
    void should_return_public_protected_package_getters() {
        var methods = Reflects.getGetterMethods( new DemoPojo(), ModifierStrategy.PACKAGE);
        assertEquals(3, methods.size());
    }
    @Test
    void should_return_all_getters() {
        assertEquals(4, Reflects.getGetterMethods( new DemoPojo(), ModifierStrategy.PRIVATE).size());
    }

}