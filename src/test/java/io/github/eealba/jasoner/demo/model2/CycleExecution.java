package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class CycleExecution {

    private final TenureType tenureType;
    private final Integer sequence;
    private final Integer cyclesCompleted;
    private final Integer cyclesRemaining;
    private final Integer currentPricingSchemeVersion;
    private final Integer totalCycles;

    private CycleExecution(Builder builder) {
        cyclesRemaining = builder.cyclesRemaining;
        currentPricingSchemeVersion = builder.currentPricingSchemeVersion;
        totalCycles = builder.totalCycles;
        tenureType = Objects.requireNonNull(builder.tenureType);
        sequence = Objects.requireNonNull(builder.sequence);
        cyclesCompleted = Objects.requireNonNull(builder.cyclesCompleted);
    }

    public TenureType tenureType() {
        return tenureType;
    }

    public Integer sequence() {
        return sequence;
    }

    public Integer cyclesCompleted() {
        return cyclesCompleted;
    }

    public Integer cyclesRemaining() {
        return cyclesRemaining;
    }

    public Integer currentPricingSchemeVersion() {
        return currentPricingSchemeVersion;
    }

    public Integer totalCycles() {
        return totalCycles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private TenureType tenureType;
        private Integer sequence;
        private Integer cyclesCompleted;
        private Integer cyclesRemaining;
        private Integer currentPricingSchemeVersion;
        private Integer totalCycles;

        public Builder tenureType(TenureType value) {
            tenureType = value;
            return this;
        }

        public Builder sequence(Integer value) {
            sequence = value;
            return this;
        }

        public Builder cyclesCompleted(Integer value) {
            cyclesCompleted = value;
            return this;
        }

        public Builder cyclesRemaining(Integer value) {
            cyclesRemaining = value;
            return this;
        }

        public Builder currentPricingSchemeVersion(Integer value) {
            currentPricingSchemeVersion = value;
            return this;
        }

        public Builder totalCycles(Integer value) {
            totalCycles = value;
            return this;
        }

        public CycleExecution build() {
            return new CycleExecution(this);
        }

    }
    /**
     * The type of the billing cycle.
     */
    public enum TenureType {
        REGULAR,
        TRIAL
    }
}

