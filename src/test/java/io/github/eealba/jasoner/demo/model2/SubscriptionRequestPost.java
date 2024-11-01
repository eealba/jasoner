package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;
import java.util.Objects;

public class SubscriptionRequestPost {

    private final String planId;
    private final Instant startTime;
    private final String quantity;
    private final Money shippingAmount;
    private final SubscriberRequest subscriber;
    private final Boolean autoRenewal;
    private final ApplicationContext applicationContext;
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

    public String planId() {
        return planId;
    }

    public Instant startTime() {
        return startTime;
    }

    public String quantity() {
        return quantity;
    }

    public Money shippingAmount() {
        return shippingAmount;
    }

    public SubscriberRequest subscriber() {
        return subscriber;
    }

    public Boolean autoRenewal() {
        return autoRenewal;
    }

    public ApplicationContext applicationContext() {
        return applicationContext;
    }

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

        public Builder planId(String value) {
            planId = value;
            return this;
        }

        public Builder startTime(Instant value) {
            startTime = value;
            return this;
        }

        public Builder quantity(String value) {
            quantity = value;
            return this;
        }

        public Builder shippingAmount(Money value) {
            shippingAmount = value;
            return this;
        }

        public Builder subscriber(SubscriberRequest value) {
            subscriber = value;
            return this;
        }

        public Builder autoRenewal(Boolean value) {
            autoRenewal = value;
            return this;
        }

        public Builder applicationContext(ApplicationContext value) {
            applicationContext = value;
            return this;
        }

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

