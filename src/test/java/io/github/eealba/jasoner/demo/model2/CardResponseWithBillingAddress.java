package io.github.eealba.jasoner.demo.model2;



public class CardResponseWithBillingAddress {

    private final String lastDigits;
    private final CardBrand brand;
    private final Type type;
    private final AuthenticationResponse authenticationResult;
    private final String name;
    private final AddressPortable billingAddress;
    private final DateYearMonth expiry;
    private final CurrencyCode currencyCode;

    private CardResponseWithBillingAddress(Builder builder) {
        lastDigits = builder.lastDigits;
        brand = builder.brand;
        type = builder.type;
        authenticationResult = builder.authenticationResult;
        name = builder.name;
        billingAddress = builder.billingAddress;
        expiry = builder.expiry;
        currencyCode = builder.currencyCode;

    }

    public String lastDigits() {
        return lastDigits;
    }

    public CardBrand brand() {
        return brand;
    }

    public Type type() {
        return type;
    }

    public AuthenticationResponse authenticationResult() {
        return authenticationResult;
    }

    public String name() {
        return name;
    }

    public AddressPortable billingAddress() {
        return billingAddress;
    }

    public DateYearMonth expiry() {
        return expiry;
    }

    public CurrencyCode currencyCode() {
        return currencyCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String lastDigits;
        private CardBrand brand;
        private Type type;
        private AuthenticationResponse authenticationResult;
        private String name;
        private AddressPortable billingAddress;
        private DateYearMonth expiry;
        private CurrencyCode currencyCode;

        public Builder lastDigits(String value) {
            lastDigits = value;
            return this;
        }

        public Builder brand(CardBrand value) {
            brand = value;
            return this;
        }

        public Builder type(Type value) {
            type = value;
            return this;
        }

        public Builder authenticationResult(AuthenticationResponse value) {
            authenticationResult = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder billingAddress(AddressPortable value) {
            billingAddress = value;
            return this;
        }

        public Builder expiry(DateYearMonth value) {
            expiry = value;
            return this;
        }

        public Builder currencyCode(CurrencyCode value) {
            currencyCode = value;
            return this;
        }

        public CardResponseWithBillingAddress build() {
            return new CardResponseWithBillingAddress(this);
        }

    }
    /**
     * The payment card type.
     */
    public enum Type {
        CREDIT,
        DEBIT,
        PREPAID,
        UNKNOWN
    }
}

