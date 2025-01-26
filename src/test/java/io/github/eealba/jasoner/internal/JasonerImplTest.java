package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.JasonerTransient;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.SerializationStrategy;
import io.github.eealba.jasoner.demo.model1.DemoPojo;
import io.github.eealba.jasoner.demo.model1.DemoPojo2;
import lombok.Data;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static io.github.eealba.jasoner.internal.Helper.PRIVATE_JASONER;
import static io.github.eealba.jasoner.internal.Helper.readResource;
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
    @Test
    void should_deserialize_empty_object() {
        var json = "{}";

        assertNotNull(json);

        var obj = PRIVATE_JASONER.fromJson(json, DemoPojo2.class);

        assertNotNull(obj);
    }

    @Test
    void should_serialize_and_deserialize_plan_with_pojo() throws URISyntaxException, IOException {
        var jsonPlanExpected = readResource("plan.json");
        serializeAndDeserialize(jsonPlanExpected,  io.github.eealba.jasoner.demo.model1.Plan.class);

    }

    @Test
    void should_serialize_and_deserialize_plan_pojo_with_builder() throws URISyntaxException, IOException {
        var jsonPlanExpected = readResource("paypal/plan.json");
        serializeAndDeserialize(jsonPlanExpected,  io.github.eealba.jasoner.demo.model2.Plan.class);
    }

    @Test
    void should_not_serialize_field_with_JasonerTransient(){
        var user = getUser();

        var jasoner = JasonerBuilder.create(JasonerConfig.builder()
                .serializationStrategy(SerializationStrategy.FIELD)
                .modifierStrategy(ModifierStrategy.PRIVATE).build());

        var json = jasoner.toJson(user);

        assertFalse(json.contains("password"));

        assertNotNull(json);

        var user2 = jasoner.fromJson(json, User.class);
        assertNotNull(user2);
        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getAge(), user2.getAge());
        assertNull(user2.getPassword());
    }
    @Test
    void should_not_deserialize_field_with_JasonerTransient(){
        String json = "{\"name\":\"John\",\"age\":30,\"password\":\"123456\"}";
        Jasoner jasoner = JasonerBuilder.create(JasonerConfig.builder()
                .serializationStrategy(SerializationStrategy.FIELD)
                .modifierStrategy(ModifierStrategy.PRIVATE).build());

        var user = jasoner.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals(30, user.getAge());
        assertNull(user.getPassword());
    }

    @Test
    void should_not_serialize_method_with_JasonerTransient(){
        var user = getUser();

        var jasoner = JasonerBuilder.create();

        var json = jasoner.toJson(user);

        assertFalse(json.contains("password"));

        assertNotNull(json);

        var user2 = jasoner.fromJson(json, User.class);
        assertNotNull(user2);
        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getAge(), user2.getAge());
        assertNull(user2.getPassword());
    }

    @Test
    void should_not_deserialize_method_with_JasonerTransient(){
        String json = "{\"name\":\"John\",\"age\":30,\"password\":\"123456\"}";
        Jasoner jasoner = JasonerBuilder.create();

        var user = jasoner.fromJson(json, User.class);
        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals(30, user.getAge());
        assertNull(user.getPassword());


    }




    private static User getUser() {
        var user = new User();
        user.setName("John");
        user.setAge(30);
        user.setPassword("123456");
        return user;
    }

    private void serializeAndDeserialize(String jsonPlanExpected, Class<?> clazz) {
        var jasoner = JasonerBuilder.create(new JasonerConfig.Builder()
                .pretty(true)
                .namingStrategy(NamingStrategy.SNAKE_CASE)
                .build());

        var plan = jasoner.fromJson(jsonPlanExpected, clazz);
        assertNotNull(plan);

        var jsonPlan = jasoner.toJson(plan);

        jsonAssertEquals(jsonPlanExpected, jsonPlan);
    }


    void jsonAssertEquals(String jsonExpected, String jsonActual) {
        try {
            System.out.println("Expected: \n" + jsonExpected);
            System.out.println("Actual: \n" + jsonActual);
            JSONAssert.assertEquals(jsonExpected, jsonActual, true);
        } catch (JSONException e) {
            throw new AssertionError(e);
        }

    }
    @Data
    static class User {
        private String name;
        private int age;
        @JasonerTransient
        private String password;
        // Getters and setters
        @JasonerTransient
        public String getPassword() {
            return password;
        }
        @JasonerTransient
        public void setPassword(String password) {
            this.password = password;
        }
    }


}