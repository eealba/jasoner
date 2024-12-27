package io.github.eealba.example;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The full name representation like Mr J Smith.
 */
@JasonerSingleVO
public record FullName(String value) {

    public FullName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        this.value = value;
    }

}