package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class BillingCycle {

    private final PricingScheme pricingScheme;
    private final Frequency frequency;
    private final TenureType tenureType;
    private final Integer sequence;
    private final Integer totalCycles;

    private BillingCycle(Builder builder) {
        pricingScheme = builder.pricingScheme;
        totalCycles = builder.totalCycles;
        frequency = Objects.requireNonNull(builder.frequency);
        tenureType = Objects.requireNonNull(builder.tenureType);
        sequence = Objects.requireNonNull(builder.sequence);
    }

    public PricingScheme pricingScheme() {
        return pricingScheme;
    }

    public Frequency frequency() {
        return frequency;
    }

    public TenureType tenureType() {
        return tenureType;
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
        private Frequency frequency;
        private TenureType tenureType;
        private Integer sequence;
        private Integer totalCycles;

        public Builder pricingScheme(PricingScheme value) {
            pricingScheme = value;
            return this;
        }

        public Builder frequency(Frequency value) {
            frequency = value;
            return this;
        }

        public Builder tenureType(TenureType value) {
            tenureType = value;
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

        public BillingCycle build() {
            return new BillingCycle(this);
        }

    }
    /**
     * The tenure type of the billing cycle. In case of a plan having trial cycle, only 2 trial cycles are allowed per plan.
     */
    public enum TenureType {
        REGULAR,
        TRIAL
    }
}

