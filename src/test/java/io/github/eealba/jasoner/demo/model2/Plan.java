package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;
import java.util.List;

public class Plan {

    private final String id;
    private final String productId;
    private final String name;
    private final Status status;
    private final String description;
    private final List<BillingCycle> billingCycles;
    private final PaymentPreferences paymentPreferences;
    private final Taxes taxes;
    private final Boolean quantitySupported;
    private final Instant createTime;
    private final Instant updateTime;
    private final List<LinkDescription> links;

    private Plan(Builder builder) {
        id = builder.id;
        productId = builder.productId;
        name = builder.name;
        status = builder.status;
        description = builder.description;
        billingCycles = builder.billingCycles;
        paymentPreferences = builder.paymentPreferences;
        taxes = builder.taxes;
        quantitySupported = builder.quantitySupported;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        links = builder.links;

    }

    public String id() {
        return id;
    }

    public String productId() {
        return productId;
    }

    public String name() {
        return name;
    }

    public Status status() {
        return status;
    }

    public String description() {
        return description;
    }

    public List<BillingCycle> billingCycles() {
        return billingCycles;
    }

    public PaymentPreferences paymentPreferences() {
        return paymentPreferences;
    }

    public Taxes taxes() {
        return taxes;
    }

    public Boolean quantitySupported() {
        return quantitySupported;
    }

    public Instant createTime() {
        return createTime;
    }

    public Instant updateTime() {
        return updateTime;
    }

    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String productId;
        private String name;
        private Status status;
        private String description;
        private List<BillingCycle> billingCycles;
        private PaymentPreferences paymentPreferences;
        private Taxes taxes;
        private Boolean quantitySupported;
        private Instant createTime;
        private Instant updateTime;
        private List<LinkDescription> links;

        public Builder id(String value) {
            id = value;
            return this;
        }

        public Builder productId(String value) {
            productId = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder status(Status value) {
            status = value;
            return this;
        }

        public Builder description(String value) {
            description = value;
            return this;
        }

        public Builder billingCycles(List<BillingCycle> value) {
            billingCycles = value;
            return this;
        }

        public Builder paymentPreferences(PaymentPreferences value) {
            paymentPreferences = value;
            return this;
        }

        public Builder taxes(Taxes value) {
            taxes = value;
            return this;
        }

        public Builder quantitySupported(Boolean value) {
            quantitySupported = value;
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

        public Builder links(List<LinkDescription> value) {
            links = value;
            return this;
        }

        public Plan build() {
            return new Plan(this);
        }

    }
    /**
     * The plan status.
     */
    public enum Status {
        CREATED,
        INACTIVE,
        ACTIVE
    }
}

