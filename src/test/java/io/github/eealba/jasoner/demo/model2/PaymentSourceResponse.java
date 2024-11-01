package io.github.eealba.jasoner.demo.model2;



public class PaymentSourceResponse {

    private final CardResponseWithBillingAddress card;

    private PaymentSourceResponse(Builder builder) {
        card = builder.card;

    }

    public CardResponseWithBillingAddress card() {
        return card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CardResponseWithBillingAddress card;

        public Builder card(CardResponseWithBillingAddress value) {
            card = value;
            return this;
        }

        public PaymentSourceResponse build() {
            return new PaymentSourceResponse(this);
        }

    }

}

