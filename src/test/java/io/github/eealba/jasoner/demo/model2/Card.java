package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;

public class Card {

    
    private final String id;
    
    private final String name;
    
    private final String number;
    
    private final DateYearMonth expiry;
    @JasonerProperty("security_code")
    private final String securityCode;
    @JasonerProperty("last_digits")
    private final String lastDigits;
    @JasonerProperty("card_type")
    private final CardBrand cardType;
    @JasonerProperty("billing_address")
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

    @JasonerProperty("security_code")
    public String securityCode() {
        return securityCode;
    }

    @JasonerProperty("last_digits")
    public String lastDigits() {
        return lastDigits;
    }

    @JasonerProperty("card_type")
    public CardBrand cardType() {
        return cardType;
    }

    @JasonerProperty("billing_address")
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

        @JasonerProperty("security_code")
        public Builder securityCode(String value) {
            securityCode = value;
            return this;
        }

        @JasonerProperty("last_digits")
        public Builder lastDigits(String value) {
            lastDigits = value;
            return this;
        }

        @JasonerProperty("card_type")
        public Builder cardType(CardBrand value) {
            cardType = value;
            return this;
        }

        @JasonerProperty("billing_address")
        public Builder billingAddress(AddressPortable value) {
            billingAddress = value;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }

}

