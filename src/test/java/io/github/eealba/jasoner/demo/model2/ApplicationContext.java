package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class ApplicationContext {

    private final String brandName;
    private final Language locale;
    private final ShippingPreference shippingPreference;
    private final UserAction userAction;
    private final PaymentMethod paymentMethod;
    private final String returnUrl;
    private final String cancelUrl;

    private ApplicationContext(Builder builder) {
        brandName = builder.brandName;
        locale = builder.locale;
        shippingPreference = builder.shippingPreference;
        userAction = builder.userAction;
        paymentMethod = builder.paymentMethod;
        returnUrl = Objects.requireNonNull(builder.returnUrl);
        cancelUrl = Objects.requireNonNull(builder.cancelUrl);
    }

    public String brandName() {
        return brandName;
    }

    public Language locale() {
        return locale;
    }

    public ShippingPreference shippingPreference() {
        return shippingPreference;
    }

    public UserAction userAction() {
        return userAction;
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    public String returnUrl() {
        return returnUrl;
    }

    public String cancelUrl() {
        return cancelUrl;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String brandName;
        private Language locale;
        private ShippingPreference shippingPreference;
        private UserAction userAction;
        private PaymentMethod paymentMethod;
        private String returnUrl;
        private String cancelUrl;

        public Builder brandName(String value) {
            brandName = value;
            return this;
        }

        public Builder locale(Language value) {
            locale = value;
            return this;
        }

        public Builder shippingPreference(ShippingPreference value) {
            shippingPreference = value;
            return this;
        }

        public Builder userAction(UserAction value) {
            userAction = value;
            return this;
        }

        public Builder paymentMethod(PaymentMethod value) {
            paymentMethod = value;
            return this;
        }

        public Builder returnUrl(String value) {
            returnUrl = value;
            return this;
        }

        public Builder cancelUrl(String value) {
            cancelUrl = value;
            return this;
        }

        public ApplicationContext build() {
            return new ApplicationContext(this);
        }

    }
    /**
     * The location from which the shipping address is derived.
     */
    public enum ShippingPreference {
        GET_FROM_FILE,
        NO_SHIPPING,
        SET_PROVIDED_ADDRESS
    }
    /**
     * Configures the label name to `Continue` or `Subscribe Now` for subscription consent experience.
     */
    public enum UserAction {
        CONTINUE,
        SUBSCRIBE_NOW
    }
}

