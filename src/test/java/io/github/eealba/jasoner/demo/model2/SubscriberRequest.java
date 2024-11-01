package io.github.eealba.jasoner.demo.model2;



public class SubscriberRequest {

    private final ShippingDetail shippingAddress;
    private final PaymentSource paymentSource;

    private SubscriberRequest(Builder builder) {
        shippingAddress = builder.shippingAddress;
        paymentSource = builder.paymentSource;

    }

    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    public PaymentSource paymentSource() {
        return paymentSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ShippingDetail shippingAddress;
        private PaymentSource paymentSource;

        public Builder shippingAddress(ShippingDetail value) {
            shippingAddress = value;
            return this;
        }

        public Builder paymentSource(PaymentSource value) {
            paymentSource = value;
            return this;
        }

        public SubscriberRequest build() {
            return new SubscriberRequest(this);
        }

    }

}

