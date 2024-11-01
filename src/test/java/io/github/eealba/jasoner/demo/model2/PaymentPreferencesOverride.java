package io.github.eealba.jasoner.demo.model2;



public class PaymentPreferencesOverride {

    private final Boolean autoBillOutstanding;
    private final Money setupFee;
    private final SetupFeeFailureAction setupFeeFailureAction;
    private final Integer paymentFailureThreshold;

    private PaymentPreferencesOverride(Builder builder) {
        autoBillOutstanding = builder.autoBillOutstanding;
        setupFee = builder.setupFee;
        setupFeeFailureAction = builder.setupFeeFailureAction;
        paymentFailureThreshold = builder.paymentFailureThreshold;

    }

    public Boolean autoBillOutstanding() {
        return autoBillOutstanding;
    }

    public Money setupFee() {
        return setupFee;
    }

    public SetupFeeFailureAction setupFeeFailureAction() {
        return setupFeeFailureAction;
    }

    public Integer paymentFailureThreshold() {
        return paymentFailureThreshold;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Boolean autoBillOutstanding;
        private Money setupFee;
        private SetupFeeFailureAction setupFeeFailureAction;
        private Integer paymentFailureThreshold;

        public Builder autoBillOutstanding(Boolean value) {
            autoBillOutstanding = value;
            return this;
        }

        public Builder setupFee(Money value) {
            setupFee = value;
            return this;
        }

        public Builder setupFeeFailureAction(SetupFeeFailureAction value) {
            setupFeeFailureAction = value;
            return this;
        }

        public Builder paymentFailureThreshold(Integer value) {
            paymentFailureThreshold = value;
            return this;
        }

        public PaymentPreferencesOverride build() {
            return new PaymentPreferencesOverride(this);
        }

    }
    /**
     * The action to take on the subscription if the initial payment for the setup fails.
     */
    public enum SetupFeeFailureAction {
        CONTINUE,
        CANCEL
    }
}

