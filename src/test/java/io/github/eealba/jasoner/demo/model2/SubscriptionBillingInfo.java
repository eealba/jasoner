package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class SubscriptionBillingInfo {

    private final Money outstandingBalance;
    private final List<CycleExecution> cycleExecutions;
    private final LastPaymentDetails lastPayment;
    private final Instant nextBillingTime;
    private final Instant finalPaymentTime;
    private final Integer failedPaymentsCount;
    private final FailedPaymentDetails lastFailedPayment;

    private SubscriptionBillingInfo(Builder builder) {
        cycleExecutions = builder.cycleExecutions;
        lastPayment = builder.lastPayment;
        nextBillingTime = builder.nextBillingTime;
        finalPaymentTime = builder.finalPaymentTime;
        lastFailedPayment = builder.lastFailedPayment;
        outstandingBalance = Objects.requireNonNull(builder.outstandingBalance);
        failedPaymentsCount = Objects.requireNonNull(builder.failedPaymentsCount);
    }

    public Money outstandingBalance() {
        return outstandingBalance;
    }

    public List<CycleExecution> cycleExecutions() {
        return cycleExecutions;
    }

    public LastPaymentDetails lastPayment() {
        return lastPayment;
    }

    public Instant nextBillingTime() {
        return nextBillingTime;
    }

    public Instant finalPaymentTime() {
        return finalPaymentTime;
    }

    public Integer failedPaymentsCount() {
        return failedPaymentsCount;
    }

    public FailedPaymentDetails lastFailedPayment() {
        return lastFailedPayment;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money outstandingBalance;
        private List<CycleExecution> cycleExecutions;
        private LastPaymentDetails lastPayment;
        private Instant nextBillingTime;
        private Instant finalPaymentTime;
        private Integer failedPaymentsCount;
        private FailedPaymentDetails lastFailedPayment;

        public Builder outstandingBalance(Money value) {
            outstandingBalance = value;
            return this;
        }

        public Builder cycleExecutions(List<CycleExecution> value) {
            cycleExecutions = value;
            return this;
        }

        public Builder lastPayment(LastPaymentDetails value) {
            lastPayment = value;
            return this;
        }

        public Builder nextBillingTime(Instant value) {
            nextBillingTime = value;
            return this;
        }

        public Builder finalPaymentTime(Instant value) {
            finalPaymentTime = value;
            return this;
        }

        public Builder failedPaymentsCount(Integer value) {
            failedPaymentsCount = value;
            return this;
        }

        public Builder lastFailedPayment(FailedPaymentDetails value) {
            lastFailedPayment = value;
            return this;
        }

        public SubscriptionBillingInfo build() {
            return new SubscriptionBillingInfo(this);
        }

    }

}

