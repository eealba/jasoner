package io.github.eealba.jasoner;

import io.github.eealba.example.Blik;
import io.github.eealba.example.ErrorDefault;
import io.github.eealba.example.PaymentInstruction;
import io.github.eealba.example.ProcessorResponse;
import io.github.eealba.example.invoices.Template;
import io.github.eealba.example.webhooks.Event;
import io.github.eealba.example.webhooks.Event2;
import io.github.eealba.jasoner.demo.model2.AddressPortable;
import io.github.eealba.jasoner.demo.model2.CountryCode;
import io.github.eealba.jasoner.demo.model2.Patch;
import io.github.eealba.jasoner.demo.model2.PatchRequest;
import io.github.eealba.jasoner.demo.model2.Plan;
import io.github.eealba.jasoner.demo.model2.PlanRequestPOST;
import io.github.eealba.jasoner.demo.model2.Subscription;
import io.github.eealba.jasoner.demo.model2.SubscriptionActivateRequest;
import io.github.eealba.jasoner.demo.model2.SubscriptionCancelRequest;
import io.github.eealba.jasoner.demo.model2.SubscriptionCaptureRequest;
import io.github.eealba.jasoner.demo.model2.SubscriptionRequestPost;
import io.github.eealba.jasoner.demo.model2.SubscriptionReviseRequest;
import io.github.eealba.jasoner.demo.model2.SubscriptionReviseResponse;
import io.github.eealba.jasoner.demo.model2.SubscriptionSuspendRequest;
import io.github.eealba.jasoner.demo.model2.UpdatePricingSchemesListRequest;
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
    private static final Jasoner JASONER2 = JasonerBuilder.create(JasonerConfig.builder()
            .pretty(true)
            .build());
    private static final String EXAMPLES = "/paypal/";

    @Test
    void should_serialize_and_deserialize_paypal_patch_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "patch_request.json"), PatchRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_plan() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "plan.json"), Plan.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_plan_request_POST() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "plan_request_POST.json"), PlanRequestPOST.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription.json"), Subscription.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_activate_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_activate_request.json"),
                SubscriptionActivateRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_cancel_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_cancel_request.json"),
                SubscriptionCancelRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_capture_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_capture_request.json"),
                SubscriptionCaptureRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_request_post() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_request_post.json"),
                SubscriptionRequestPost.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_revise_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_revise_request.json"),
                SubscriptionReviseRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_revise_response() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_revise_response.json"),
                SubscriptionReviseResponse.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_suspend_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_suspend_request.json"),
                SubscriptionSuspendRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_update_pricing_schemes_list_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "update_pricing_schemes_list_request.json"),
                UpdatePricingSchemesListRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_processor_response() throws IOException, JSONException {
        executeAndCompare(readResource("/processor_response.json"), ProcessorResponse.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_payment_instruction() throws IOException, JSONException {
        executeAndCompare(readResource("/payment_instruction.json"), PaymentInstruction.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_blik() throws IOException, JSONException {
        executeAndCompare(readResource("/blik.json"), Blik.class);
    }
    @Test
    void error_serialize_deserialize() throws IOException, JSONException {
        executeAndCompare(readResource("/error.json"), ErrorDefault.class);
    }
    @Test
    void error2_serialize_deserialize() throws IOException, JSONException {
        executeAndCompare(readResource("/error2.json"), ErrorDefault.class);
    }
    @Test
    void template_serialize_deserialize() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "template.json"), Template.class);
    }
    @Test
    void webhooks_events_resend_response() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "webhooks-events.resend_response.json"), Event.class);
    }

    @Test
    void webhooks_events_resend_response_with_array() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "webhooks-events.resend_response.json"), Event2.class);
    }


    @Test
    void patch_serialize() throws JSONException {
        var address = AddressPortable.builder()
                .addressLine1("123 Townsend St")
                .addressLine2("Floor 6")
                .adminArea1("San Francisco")
                .adminArea2("CA")
                .postalCode("94107")
                .countryCode(CountryCode.US)
                .build();

        var path = Patch.builder()
            .op(Patch.Op.add)
            .path("/purchase_units/@reference_id=='default'/shipping/address")
            .value(address)
            .build();

        String newJson = JASONER2.toJson(path);

        String json = """
                {
                  "op": "add",
                  "path": "/purchase_units/@reference_id=='default'/shipping/address",
                  "value": {
                    "address_line_1": "123 Townsend St",
                    "address_line_2": "Floor 6",
                    "admin_area_1": "San Francisco",
                    "admin_area_2": "CA",
                    "postal_code": "94107",
                    "country_code": "US"
                  }
                }
                """;

        JSONAssert.assertEquals(json, newJson, true);
    }

    private static void executeAndCompare(String json, Class<?> clazz) throws JSONException {
        executeAndCompare2(json, clazz);
        executeAndCompare1(json, clazz);
    }

    private static void executeAndCompare1(String json, Class<?> clazz) throws JSONException {
        var subscription = JASONER.fromJson(json, clazz);
        assertNotNull(subscription);
        String newJson = JASONER.toJson(subscription);
        JSONAssert.assertEquals(json, newJson, true);

    }

    private static void executeAndCompare2(String json, Class<?> clazz) throws JSONException {
        var subscription = JASONER2.fromJson(json, clazz);
        assertNotNull(subscription);
        String newJson = JASONER2.toJson(subscription);
        JSONAssert.assertEquals(json, newJson, true);
    }

    private static String readResource(String path) throws IOException {
        try (var inputStream = PaypalPayloadTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }


}
