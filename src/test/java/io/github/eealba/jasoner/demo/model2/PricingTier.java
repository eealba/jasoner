package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class PricingTier {

    private final String startingQuantity;
    private final String endingQuantity;
    private final Money amount;

    private PricingTier(Builder builder) {
        endingQuantity = builder.endingQuantity;
        startingQuantity = Objects.requireNonNull(builder.startingQuantity);
        amount = Objects.requireNonNull(builder.amount);
    }

    public String startingQuantity() {
        return startingQuantity;
    }

    public String endingQuantity() {
        return endingQuantity;
    }

    public Money amount() {
        return amount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String startingQuantity;
        private String endingQuantity;
        private Money amount;

        public Builder startingQuantity(String value) {
            startingQuantity = value;
            return this;
        }

        public Builder endingQuantity(String value) {
            endingQuantity = value;
            return this;
        }

        public Builder amount(Money value) {
            amount = value;
            return this;
        }

        public PricingTier build() {
            return new PricingTier(this);
        }

    }

}

