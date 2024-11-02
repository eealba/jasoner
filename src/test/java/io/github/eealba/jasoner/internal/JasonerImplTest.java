package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.demo.model1.DemoPojo;
import io.github.eealba.jasoner.demo.model1.DemoPojo2;
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
    void should_serialize_and_deserialize_plan_with_pojo() throws URISyntaxException, IOException {
        var jsonPlanExpected = readResource("plan.json");
        serializeAndDeserialize(jsonPlanExpected,  io.github.eealba.jasoner.demo.model1.Plan.class);

    }

    @Test
    void should_serialize_and_deserialize_plan_pojo_with_builder() throws URISyntaxException, IOException {
        var jsonPlanExpected = readResource("paypal/plan.json");
        serializeAndDeserialize(jsonPlanExpected,  io.github.eealba.jasoner.demo.model2.Plan.class);
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

}