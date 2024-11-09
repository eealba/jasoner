package io.github.eealba.jasoner.demo.model2;



public class PaymentSource {

    
    private final Card card;

    private PaymentSource(Builder builder) {
        card = builder.card;

    }

    
    public Card card() {
        return card;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Card card;

        
        public Builder card(Card value) {
            card = value;
            return this;
        }

        public PaymentSource build() {
            return new PaymentSource(this);
        }

    }

}

