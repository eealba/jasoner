package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The account identifier for a PayPal account.
 */
@JasonerSingleVO
public record AccountId(String value) {

    public AccountId(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^[2-9A-HJ-NP-Z]{13}$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
