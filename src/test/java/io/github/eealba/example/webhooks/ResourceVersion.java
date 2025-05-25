package io.github.eealba.example.webhooks;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The resource version in the webhook notification.
 */
@JasonerSingleVO
public record ResourceVersion(String value) {

    public ResourceVersion(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^([0-9]+.[0-9]+)$")) {
            throw new IllegalArgumentException("The value: " + value + " does not match the required pattern");
        }
        this.value = value;
    }

}
