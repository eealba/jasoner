package io.github.eealba.jasoner.demo.model2;



public class SubscriptionActivateRequest {

    private final String reason;

    private SubscriptionActivateRequest(Builder builder) {
        reason = builder.reason;

    }

    public String reason() {
        return reason;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String reason;

        public Builder reason(String value) {
            reason = value;
            return this;
        }

        public SubscriptionActivateRequest build() {
            return new SubscriptionActivateRequest(this);
        }

    }

}

