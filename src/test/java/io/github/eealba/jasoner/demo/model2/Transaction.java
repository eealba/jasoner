package io.github.eealba.jasoner.demo.model2;

import java.time.Instant;

public class Transaction {

    private final Status status;
    private final CaptureStatusDetails statusDetails;
    private final String id;
    private final AmountWithBreakdown amountWithBreakdown;
    private final Name payerName;
    private final EmailAddress payerEmail;
    private final Instant time;

    private Transaction(Builder builder) {
        status = builder.status;
        statusDetails = builder.statusDetails;
        id = builder.id;
        amountWithBreakdown = builder.amountWithBreakdown;
        payerName = builder.payerName;
        payerEmail = builder.payerEmail;
        time = builder.time;

    }

    public Status status() {
        return status;
    }

    public CaptureStatusDetails statusDetails() {
        return statusDetails;
    }

    public String id() {
        return id;
    }

    public AmountWithBreakdown amountWithBreakdown() {
        return amountWithBreakdown;
    }

    public Name payerName() {
        return payerName;
    }

    public EmailAddress payerEmail() {
        return payerEmail;
    }

    public Instant time() {
        return time;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private CaptureStatusDetails statusDetails;
        private String id;
        private AmountWithBreakdown amountWithBreakdown;
        private Name payerName;
        private EmailAddress payerEmail;
        private Instant time;

        public Builder status(Status value) {
            status = value;
            return this;
        }

        public Builder statusDetails(CaptureStatusDetails value) {
            statusDetails = value;
            return this;
        }

        public Builder id(String value) {
            id = value;
            return this;
        }

        public Builder amountWithBreakdown(AmountWithBreakdown value) {
            amountWithBreakdown = value;
            return this;
        }

        public Builder payerName(Name value) {
            payerName = value;
            return this;
        }

        public Builder payerEmail(EmailAddress value) {
            payerEmail = value;
            return this;
        }

        public Builder time(Instant value) {
            time = value;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }

    }
    /**
     * The status of the captured payment.
     */
    public enum Status {
        COMPLETED,
        DECLINED,
        PARTIALLY_REFUNDED,
        PENDING,
        REFUNDED
    }
}

