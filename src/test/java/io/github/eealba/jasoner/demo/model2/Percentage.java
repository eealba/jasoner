package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerSingleVO;

/**
 * The percentage, as a fixed-point, signed decimal number. For example, define a 19.99% interest rate as `19.99`.
 */
@JasonerSingleVO
public record Percentage(String value) {

    public Percentage(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Field value can`t be null");
        }
        if (!value.matches("^((-?[0-9]+)|(-?([0-9]+)?[.][0-9]+))$")) {
            throw new IllegalArgumentException("Invalid pattern for field value");
        }
        this.value = value;
    }

}
