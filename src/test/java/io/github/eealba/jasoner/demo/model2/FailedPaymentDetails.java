package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;
import java.util.Objects;

public class FailedPaymentDetails {

    private final Money amount;
    private final Instant time;
    private final ReasonCode reasonCode;
    private final Instant nextPaymentRetryTime;

    private FailedPaymentDetails(Builder builder) {
        reasonCode = builder.reasonCode;
        nextPaymentRetryTime = builder.nextPaymentRetryTime;
        amount = Objects.requireNonNull(builder.amount);
        time = Objects.requireNonNull(builder.time);
    }

    public Money amount() {
        return amount;
    }

    public Instant time() {
        return time;
    }

    public ReasonCode reasonCode() {
        return reasonCode;
    }

    public Instant nextPaymentRetryTime() {
        return nextPaymentRetryTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private Instant time;
        private ReasonCode reasonCode;
        private Instant nextPaymentRetryTime;

        public Builder amount(Money value) {
            amount = value;
            return this;
        }

        public Builder time(Instant value) {
            time = value;
            return this;
        }

        public Builder reasonCode(ReasonCode value) {
            reasonCode = value;
            return this;
        }

        public Builder nextPaymentRetryTime(Instant value) {
            nextPaymentRetryTime = value;
            return this;
        }

        public FailedPaymentDetails build() {
            return new FailedPaymentDetails(this);
        }

    }
    /**
     * The reason code for the payment failure.
     */
    public enum ReasonCode {
        PAYMENT_DENIED,
        INTERNAL_SERVER_ERROR,
        PAYEE_ACCOUNT_RESTRICTED,
        PAYER_ACCOUNT_RESTRICTED,
        PAYER_CANNOT_PAY,
        SENDING_LIMIT_EXCEEDED,
        TRANSACTION_RECEIVING_LIMIT_EXCEEDED,
        CURRENCY_MISMATCH
    }
}

