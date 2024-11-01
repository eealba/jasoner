package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class AmountWithBreakdown {

    private final Money grossAmount;
    private final Money totalItemAmount;
    private final Money feeAmount;
    private final Money shippingAmount;
    private final Money taxAmount;
    private final Money netAmount;

    private AmountWithBreakdown(Builder builder) {
        totalItemAmount = builder.totalItemAmount;
        feeAmount = builder.feeAmount;
        shippingAmount = builder.shippingAmount;
        taxAmount = builder.taxAmount;
        netAmount = builder.netAmount;
        grossAmount = Objects.requireNonNull(builder.grossAmount);
    }

    public Money grossAmount() {
        return grossAmount;
    }

    public Money totalItemAmount() {
        return totalItemAmount;
    }

    public Money feeAmount() {
        return feeAmount;
    }

    public Money shippingAmount() {
        return shippingAmount;
    }

    public Money taxAmount() {
        return taxAmount;
    }

    public Money netAmount() {
        return netAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money grossAmount;
        private Money totalItemAmount;
        private Money feeAmount;
        private Money shippingAmount;
        private Money taxAmount;
        private Money netAmount;

        public Builder grossAmount(Money value) {
            grossAmount = value;
            return this;
        }

        public Builder totalItemAmount(Money value) {
            totalItemAmount = value;
            return this;
        }

        public Builder feeAmount(Money value) {
            feeAmount = value;
            return this;
        }

        public Builder shippingAmount(Money value) {
            shippingAmount = value;
            return this;
        }

        public Builder taxAmount(Money value) {
            taxAmount = value;
            return this;
        }

        public Builder netAmount(Money value) {
            netAmount = value;
            return this;
        }

        public AmountWithBreakdown build() {
            return new AmountWithBreakdown(this);
        }

    }

}

