package io.github.eealba.jasoner;

import io.github.eealba.jasoner.demo.model2.Plan;
import io.github.eealba.jasoner.demo.model2.PlanRequestPOST;
import io.github.eealba.jasoner.demo.model2.Subscription;
import io.github.eealba.jasoner.demo.model2.SubscriptionActivateRequest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaypalPayloadTest {
    private static final Jasoner JASONER = JasonerBuilder.create(JasonerConfig.builder()
            .namingStrategy(NamingStrategy.SNAKE_CASE)
            .pretty(true)
            .build());

    @Test
    void should_serialize_and_deserialize_paypal_subscription() throws IOException, JSONException {
        executeAndCompare(readResource("/paypal/subscription.json"), Subscription.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_plan() throws IOException, JSONException {
        executeAndCompare(readResource("/paypal/plan.json"), Plan.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_plan_request_POST() throws IOException, JSONException {
        executeAndCompare(readResource("/paypal/plan_request_POST.json"), PlanRequestPOST.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_activate_request() throws IOException, JSONException {
        executeAndCompare(readResource("/paypal/subscription_activate_request.json"),
                SubscriptionActivateRequest.class);
    }

    private static void executeAndCompare(String json, Class<?> clazz) throws JSONException {
        var subscription = JASONER.fromJson(json, clazz);
        assertNotNull(subscription);
        String newJson = JASONER.toJson(subscription);
        JSONAssert.assertEquals(json, newJson, true);
    }

    private static String readResource(String path) throws IOException {
        try (var inputStream = PaypalPayloadTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
