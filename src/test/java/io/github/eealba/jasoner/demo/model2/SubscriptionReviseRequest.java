package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerProperty;

public class SubscriptionReviseRequest {

    @JasonerProperty("plan_id")
    private final String planId;
    
    private final String quantity;
    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    @JasonerProperty("shipping_address")
    private final ShippingDetail shippingAddress;
    @JasonerProperty("application_context")
    private final ApplicationContext applicationContext;
    
    private final PlanOverride plan;

    private SubscriptionReviseRequest(Builder builder) {
        planId = builder.planId;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        shippingAddress = builder.shippingAddress;
        applicationContext = builder.applicationContext;
        plan = builder.plan;

    }

    @JasonerProperty("plan_id")
    public String planId() {
        return planId;
    }

    
    public String quantity() {
        return quantity;
    }

    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    @JasonerProperty("shipping_address")
    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    @JasonerProperty("application_context")
    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    
    public PlanOverride plan() {
        return plan;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String planId;
        private String quantity;
        private Money shippingAmount;
        private ShippingDetail shippingAddress;
        private ApplicationContext applicationContext;
        private PlanOverride plan;

        @JasonerProperty("plan_id")
        public Builder planId(String value) {
            planId = value;
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

        @JasonerProperty("shipping_address")
        public Builder shippingAddress(ShippingDetail value) {
            shippingAddress = value;
            return this;
        }

        @JasonerProperty("application_context")
        public Builder applicationContext(ApplicationContext value) {
            applicationContext = value;
            return this;
        }

        
        public Builder plan(PlanOverride value) {
            plan = value;
            return this;
        }

        public SubscriptionReviseRequest build() {
            return new SubscriptionReviseRequest(this);
        }

    }

}

