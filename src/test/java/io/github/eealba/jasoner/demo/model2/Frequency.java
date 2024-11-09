package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

public class Frequency {

    @JasonerProperty("interval_unit")
    private final IntervalUnit intervalUnit;
    @JasonerProperty("interval_count")
    private final Integer intervalCount;

    private Frequency(Builder builder) {
        intervalCount = builder.intervalCount;
        intervalUnit = Objects.requireNonNull(builder.intervalUnit);
    }

    @JasonerProperty("interval_unit")
    public IntervalUnit intervalUnit() {
        return intervalUnit;
    }

    @JasonerProperty("interval_count")
    public Integer intervalCount() {
        return intervalCount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private IntervalUnit intervalUnit;
        private Integer intervalCount;

        @JasonerProperty("interval_unit")
        public Builder intervalUnit(IntervalUnit value) {
            intervalUnit = value;
            return this;
        }

        @JasonerProperty("interval_count")
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

