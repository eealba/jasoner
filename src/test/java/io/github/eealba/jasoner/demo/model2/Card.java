package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class Card {

    private final String id;
    private final String name;
    private final String number;
    private final DateYearMonth expiry;
    private final String securityCode;
    private final String lastDigits;
    private final CardBrand cardType;
    private final AddressPortable billingAddress;

    private Card(Builder builder) {
        id = builder.id;
        name = builder.name;
        securityCode = builder.securityCode;
        lastDigits = builder.lastDigits;
        cardType = builder.cardType;
        billingAddress = builder.billingAddress;
        number = Objects.requireNonNull(builder.number);
        expiry = Objects.requireNonNull(builder.expiry);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String number() {
        return number;
    }

    public DateYearMonth expiry() {
        return expiry;
    }

    public String securityCode() {
        return securityCode;
    }

    public String lastDigits() {
        return lastDigits;
    }

    public CardBrand cardType() {
        return cardType;
    }

    public AddressPortable billingAddress() {
        return billingAddress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String name;
        private String number;
        private DateYearMonth expiry;
        private String securityCode;
        private String lastDigits;
        private CardBrand cardType;
        private AddressPortable billingAddress;

        public Builder id(String value) {
            id = value;
            return this;
        }

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder number(String value) {
            number = value;
            return this;
        }

        public Builder expiry(DateYearMonth value) {
            expiry = value;
            return this;
        }

        public Builder securityCode(String value) {
            securityCode = value;
            return this;
        }

        public Builder lastDigits(String value) {
            lastDigits = value;
            return this;
        }

        public Builder cardType(CardBrand value) {
            cardType = value;
            return this;
        }

        public Builder billingAddress(AddressPortable value) {
            billingAddress = value;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }

}

