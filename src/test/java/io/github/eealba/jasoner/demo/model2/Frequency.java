package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class Frequency {

    private final IntervalUnit intervalUnit;
    private final Integer intervalCount;

    private Frequency(Builder builder) {
        intervalCount = builder.intervalCount;
        intervalUnit = Objects.requireNonNull(builder.intervalUnit);
    }

    public IntervalUnit intervalUnit() {
        return intervalUnit;
    }

    public Integer intervalCount() {
        return intervalCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private IntervalUnit intervalUnit;
        private Integer intervalCount;

        public Builder intervalUnit(IntervalUnit value) {
            intervalUnit = value;
            return this;
        }

        public Builder intervalCount(Integer value) {
            intervalCount = value;
            return this;
        }

        public Frequency build() {
            return new Frequency(this);
        }

    }
    /**
     * The interval at which the subscription is charged or billed.
     */
    public enum IntervalUnit {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}

