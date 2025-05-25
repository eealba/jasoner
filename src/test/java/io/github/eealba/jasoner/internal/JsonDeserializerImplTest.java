package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerProperty;
import io.github.eealba.jasoner.JsonObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JsonDeserializerImplTest {

    @Test
    void deserialize_with_pojo() throws URISyntaxException, IOException {
        String data = Helper.readResource("plan.json");
        var plan = deserialize(data, io.github.eealba.jasoner.demo.model1.Plan.class);

        assertEquals("PROD-XXCD1234QWER65782", plan.getProductId());
    }

    @Test
    void deserialize_with_immutable_builder_paypal_plan() throws URISyntaxException, IOException {
        String data = Helper.readResource("paypal/plan.json");
        var plan = deserialize(data, io.github.eealba.jasoner.demo.model2.Plan.class);

        assertEquals("PROD-XXCD1234QWER65782", plan.productId());
    }

    @Test
    void deserialize_with_immutable_builder_paypal_plan_request_post() throws URISyntaxException, IOException {
        String data = Helper.readResource("paypal/plan_request_POST.json");
        var plan = deserialize(data, io.github.eealba.jasoner.demo.model2.PlanRequestPOST.class);

        assertEquals("PROD-XXCD1234QWER65782", plan.productId());
    }

    @Test
    void deserialize_with_immutable_builder_paypal_subscription() throws URISyntaxException, IOException {
        String data = Helper.readResource("paypal/subscription.json");
        var res = deserialize(data, io.github.eealba.jasoner.demo.model2.Subscription.class);

        assertEquals("95131", res.subscriber()
                .shippingAddress().address().postalCode());
    }
    @Test
    void deserialize_with_immutable_builder_paypal_update_pricing() throws URISyntaxException, IOException {
        String data = Helper.readResource("paypal/update_pricing_schemes_list_request.json");
        var res = deserialize(data, io.github.eealba.jasoner.demo.model2.UpdatePricingSchemesListRequest.class);
        assertEquals("250", res.pricingSchemes().get(1).pricingScheme().tiers().get(1).amount().value());
    }
    @Test
    void deserialize_with_immutable_builder_paypal_subscription_actions() throws URISyntaxException, IOException {
        var data = Helper.readResource("paypal/subscription_activate_request.json");
        var res = deserialize(data, io.github.eealba.jasoner.demo.model2.SubscriptionActivateRequest.class);
        assertEquals("Reactivating the subscription", res.reason());

        data = Helper.readResource("paypal/subscription_cancel_request.json");
        var res2 = deserialize(data, io.github.eealba.jasoner.demo.model2.SubscriptionCancelRequest.class);
        assertEquals("Not satisfied with the service", res2.reason());

    }
    @Test
    void deserialize_dog_with_JasonerProperty() {
        String data = """
                {
                  "dog-name": "Falco",
                  "age": 4
                }
                """;
        var res = deserialize(data, Dog1.class);

        assertEquals("Falco", res.name);
    }

    @Test
    void deserialize_with_immutable_builder_paypal_plan_with_extra_object_does_not_exists() {
        String data = """
                {
                  "id": "P-5ML4271244454362WXNWU5NQ",
                  "product_id": "PROD-XXCD1234QWER65782",
                  "name_bad": "Video Streaming Service Plan",
                  "obj_bad": {
                    "status": "ACTIVE"
                    }
                }
                """;
        var res = deserialize(data, io.github.eealba.jasoner.demo.model2.Plan.class);

        assertEquals("P-5ML4271244454362WXNWU5NQ", res.id());
    }
    @Test
    void deserialize_with_JsonObject() throws URISyntaxException, IOException {
        String data = Helper.readResource("plan.json");
        var jsonObject = deserialize(data, JsonObject.class);

        assertEquals("PROD-XXCD1234QWER65782", jsonObject.getString("product_id"));
        assertFalse(jsonObject.getBoolean("taxes.inclusive"));
        assertEquals("3", jsonObject.getString("billing_cycles.0.pricing_scheme.fixed_price.value"));
    }





    private static <T> T deserialize(String data, Class<T> clazz) {
        JsonDeserializer deserializer = new JsonDeserializerImpl();
        return deserializer.deserialize(data, clazz);
    }

    static class Dog1 {
        @JasonerProperty("dog-name")
        public String name;
        public int age;
    }

}