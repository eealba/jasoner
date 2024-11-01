package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;
import java.util.List;

public class Subscription {

    private final Status status;
    private final String statusChangeNote;
    private final Instant statusUpdateTime;
    private final String id;
    private final String planId;
    private final Instant startTime;
    private final String quantity;
    private final Money shippingAmount;
    private final Subscriber subscriber;
    private final SubscriptionBillingInfo billingInfo;
    private final Instant createTime;
    private final Instant updateTime;
    private final String customId;
    private final Boolean planOverridden;
    private final Plan plan;
    private final List<LinkDescription> links;

    private Subscription(Builder builder) {
        status = builder.status;
        statusChangeNote = builder.statusChangeNote;
        statusUpdateTime = builder.statusUpdateTime;
        id = builder.id;
        planId = builder.planId;
        startTime = builder.startTime;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        subscriber = builder.subscriber;
        billingInfo = builder.billingInfo;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        customId = builder.customId;
        planOverridden = builder.planOverridden;
        plan = builder.plan;
        links = builder.links;

    }

    public Status status() {
        return status;
    }

    public String statusChangeNote() {
        return statusChangeNote;
    }

    public Instant statusUpdateTime() {
        return statusUpdateTime;
    }

    public String id() {
        return id;
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

    public Subscriber subscriber() {
        return subscriber;
    }

    public SubscriptionBillingInfo billingInfo() {
        return billingInfo;
    }

    public Instant createTime() {
        return createTime;
    }

    public Instant updateTime() {
        return updateTime;
    }

    public String customId() {
        return customId;
    }

    public Boolean planOverridden() {
        return planOverridden;
    }

    public Plan plan() {
        return plan;
    }

    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private String statusChangeNote;
        private Instant statusUpdateTime;
        private String id;
        private String planId;
        private Instant startTime;
        private String quantity;
        private Money shippingAmount;
        private Subscriber subscriber;
        private SubscriptionBillingInfo billingInfo;
        private Instant createTime;
        private Instant updateTime;
        private String customId;
        private Boolean planOverridden;
        private Plan plan;
        private List<LinkDescription> links;

        public Builder status(Status value) {
            status = value;
            return this;
        }

        public Builder statusChangeNote(String value) {
            statusChangeNote = value;
            return this;
        }

        public Builder statusUpdateTime(Instant value) {
            statusUpdateTime = value;
            return this;
        }

        public Builder id(String value) {
            id = value;
            return this;
        }

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

        public Builder subscriber(Subscriber value) {
            subscriber = value;
            return this;
        }

        public Builder billingInfo(SubscriptionBillingInfo value) {
            billingInfo = value;
            return this;
        }

        public Builder createTime(Instant value) {
            createTime = value;
            return this;
        }

        public Builder updateTime(Instant value) {
            updateTime = value;
            return this;
        }

        public Builder customId(String value) {
            customId = value;
            return this;
        }

        public Builder planOverridden(Boolean value) {
            planOverridden = value;
            return this;
        }

        public Builder plan(Plan value) {
            plan = value;
            return this;
        }

        public Builder links(List<LinkDescription> value) {
            links = value;
            return this;
        }

        public Subscription build() {
            return new Subscription(this);
        }

    }
    /**
     * The status of the subscription.
     */
    public enum Status {
        APPROVAL_PENDING,
        APPROVED,
        ACTIVE,
        SUSPENDED,
        CANCELLED,
        EXPIRED
    }
}

