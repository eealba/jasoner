package io.github.eealba.jasoner;

import java.math.BigDecimal;

/**
 * The interface Token.
 */
public interface Token {
    /**
     * Type token type.
     *
     * @return the token type
     */
    TokenType type();

    /**
     * Boolean value boolean.
     *
     * @return the boolean
     */
    Boolean booleanValue();

    /**
     * String value string.
     *
     * @return the string
     */
    String stringValue();

    /**
     * Numeric value big decimal.
     *
     * @return the big decimal
     */
    BigDecimal numericValue();

    /**
     * Type value object.
     *
     * @param type the type
     * @return the object
     */
    Object typeValue(Class<?> type);

    /**
     * Value object.
     *
     * @return the object
     */
    Object value();
}
