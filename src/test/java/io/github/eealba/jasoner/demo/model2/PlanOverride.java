package io.github.eealba.jasoner.demo.model2;

import java.util.List;

public class PlanOverride {

    private final List<BillingCycleOverride> billingCycles;
    private final PaymentPreferencesOverride paymentPreferences;
    private final TaxesOverride taxes;

    private PlanOverride(Builder builder) {
        billingCycles = builder.billingCycles;
        paymentPreferences = builder.paymentPreferences;
        taxes = builder.taxes;

    }

    public List<BillingCycleOverride> billingCycles() {
        return billingCycles;
    }

    public PaymentPreferencesOverride paymentPreferences() {
        return paymentPreferences;
    }

    public TaxesOverride taxes() {
        return taxes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<BillingCycleOverride> billingCycles;
        private PaymentPreferencesOverride paymentPreferences;
        private TaxesOverride taxes;

        public Builder billingCycles(List<BillingCycleOverride> value) {
            billingCycles = value;
            return this;
        }

        public Builder paymentPreferences(PaymentPreferencesOverride value) {
            paymentPreferences = value;
            return this;
        }

        public Builder taxes(TaxesOverride value) {
            taxes = value;
            return this;
        }

        public PlanOverride build() {
            return new PlanOverride(this);
        }

    }

}

