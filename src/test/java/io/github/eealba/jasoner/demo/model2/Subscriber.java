package io.github.eealba.jasoner.demo.model2;



public class Subscriber {

    private final ShippingDetail shippingAddress;
    private final PaymentSourceResponse paymentSource;

    private Subscriber(Builder builder) {
        shippingAddress = builder.shippingAddress;
        paymentSource = builder.paymentSource;

    }

    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    public PaymentSourceResponse paymentSource() {
        return paymentSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ShippingDetail shippingAddress;
        private PaymentSourceResponse paymentSource;

        public Builder shippingAddress(ShippingDetail value) {
            shippingAddress = value;
            return this;
        }

        public Builder paymentSource(PaymentSourceResponse value) {
            paymentSource = value;
            return this;
        }

        public Subscriber build() {
            return new Subscriber(this);
        }

    }

}

