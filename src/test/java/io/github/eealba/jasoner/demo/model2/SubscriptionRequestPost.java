package io.github.eealba.jasoner.demo.model2;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.Objects;

public class SubscriptionRequestPost {

    @JasonerProperty("plan_id")
    private final String planId;
    @JasonerProperty("start_time")
    private final Instant startTime;
    
    private final String quantity;
    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    
    private final SubscriberRequest subscriber;
    @JasonerProperty("auto_renewal")
    private final Boolean autoRenewal;
    @JasonerProperty("application_context")
    private final ApplicationContext applicationContext;
    @JasonerProperty("custom_id")
    private final String customId;
    
    private final PlanOverride plan;

    private SubscriptionRequestPost(Builder builder) {
        startTime = builder.startTime;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        subscriber = builder.subscriber;
        autoRenewal = builder.autoRenewal;
        applicationContext = builder.applicationContext;
        customId = builder.customId;
        plan = builder.plan;
        planId = Objects.requireNonNull(builder.planId);
    }

    @JasonerProperty("plan_id")
    public String planId() {
        return planId;
    }

    @JasonerProperty("start_time")
    public Instant startTime() {
        return startTime;
    }

    
    public String quantity() {
        return quantity;
    }

    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    
    public SubscriberRequest subscriber() {
        return subscriber;
    }

    @JasonerProperty("auto_renewal")
    public Boolean autoRenewal() {
        return autoRenewal;
    }

    @JasonerProperty("application_context")
    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    
    public PlanOverride plan() {
        return plan;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String planId;
        private Instant startTime;
        private String quantity;
        private Money shippingAmount;
        private SubscriberRequest subscriber;
        private Boolean autoRenewal;
        private ApplicationContext applicationContext;
        private String customId;
        private PlanOverride plan;

        @JasonerProperty("plan_id")
        public Builder planId(String value) {
            planId = value;
            return this;
        }

        @JasonerProperty("start_time")
        public Builder startTime(Instant value) {
            startTime = value;
            return this;
        }

        
        public Builder quantity(String value) {
            quantity = value;
            return this;
        }

        @JasonerProperty("shipping_amount")
        public Builder shippingAmount(Money value) {
            shippingAmount = value;
            return this;
        }

        
        public Builder subscriber(SubscriberRequest value) {
            subscriber = value;
            return this;
        }

        @JasonerProperty("auto_renewal")
        public Builder autoRenewal(Boolean value) {
            autoRenewal = value;
            return this;
        }

        @JasonerProperty("application_context")
        public Builder applicationContext(ApplicationContext value) {
            applicationContext = value;
            return this;
        }

        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            customId = value;
            return this;
        }

        
        public Builder plan(PlanOverride value) {
            plan = value;
            return this;
        }

        public SubscriptionRequestPost build() {
            return new SubscriptionRequestPost(this);
        }

    }

}

