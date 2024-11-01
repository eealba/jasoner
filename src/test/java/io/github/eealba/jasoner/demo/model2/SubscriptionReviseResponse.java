package io.github.eealba.jasoner.demo.model2;

import java.util.List;

public class SubscriptionReviseResponse {

    private final String planId;
    private final String quantity;
    private final Money shippingAmount;
    private final ShippingDetail shippingAddress;
    private final ApplicationContext applicationContext;
    private final PlanOverride plan;
    private final Boolean planOverridden;
    private final List<LinkDescription> links;

    private SubscriptionReviseResponse(Builder builder) {
        planId = builder.planId;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        shippingAddress = builder.shippingAddress;
        applicationContext = builder.applicationContext;
        plan = builder.plan;
        planOverridden = builder.planOverridden;
        links = builder.links;

    }

    public String planId() {
        return planId;
    }

    public String quantity() {
        return quantity;
    }

    public Money shippingAmount() {
        return shippingAmount;
    }

    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    public PlanOverride plan() {
        return plan;
    }

    public Boolean planOverridden() {
        return planOverridden;
    }

    public List<LinkDescription> links() {
        return links;
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
        private Boolean planOverridden;
        private List<LinkDescription> links;

        public Builder planId(String value) {
            planId = value;
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

        public Builder shippingAddress(ShippingDetail value) {
            shippingAddress = value;
            return this;
        }

        public Builder applicationContext(ApplicationContext value) {
            applicationContext = value;
            return this;
        }

        public Builder plan(PlanOverride value) {
            plan = value;
            return this;
        }

        public Builder planOverridden(Boolean value) {
            planOverridden = value;
            return this;
        }

        public Builder links(List<LinkDescription> value) {
            links = value;
            return this;
        }

        public SubscriptionReviseResponse build() {
            return new SubscriptionReviseResponse(this);
        }

    }

}

