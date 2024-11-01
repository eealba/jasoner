package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class BillingCycleOverride {

    private final PricingScheme pricingScheme;
    private final Integer sequence;
    private final Integer totalCycles;

    private BillingCycleOverride(Builder builder) {
        pricingScheme = builder.pricingScheme;
        totalCycles = builder.totalCycles;
        sequence = Objects.requireNonNull(builder.sequence);
    }

    public PricingScheme pricingScheme() {
        return pricingScheme;
    }

    public Integer sequence() {
        return sequence;
    }

    public Integer totalCycles() {
        return totalCycles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PricingScheme pricingScheme;
        private Integer sequence;
        private Integer totalCycles;

        public Builder pricingScheme(PricingScheme value) {
            pricingScheme = value;
            return this;
        }

        public Builder sequence(Integer value) {
            sequence = value;
            return this;
        }

        public Builder totalCycles(Integer value) {
            totalCycles = value;
            return this;
        }

        public BillingCycleOverride build() {
            return new BillingCycleOverride(this);
        }

    }

}

