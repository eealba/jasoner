/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * The class TokenImpl.
 * This class implements the Token interface to represent a JSON token.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class TokenImpl implements Token {
    /**
     * The Object start token.
     */
    static final Token OBJECT_START = new TokenImpl(TokenType.OBJECT_START, "{");
    /**
     * The Object end token.
     */
    static final Token OBJECT_END = new TokenImpl(TokenType.OBJECT_END, "}");
    /**
     * The Array start token.
     */
    static final Token ARRAY_START = new TokenImpl(TokenType.ARRAY_START, "[");
    /**
     * The Array end token.
     */
    static final Token ARRAY_END = new TokenImpl(TokenType.ARRAY_END, "]");
    /**
     * The Colon token.
     */
    static final Token COLON = new TokenImpl(TokenType.COLON, ":");
    /**
     * The Comma token.
     */
    static final Token COMMA = new TokenImpl(TokenType.COMMA, ",");
    /**
     * The False token.
     */
    static final Token FALSE = new TokenImpl(TokenType.FALSE, "false");
    /**
     * The True token.
     */
    static final Token TRUE = new TokenImpl(TokenType.TRUE, "true");
    /**
     * The Null token.
     */
    static final Token NULL = new TokenImpl(TokenType.NULL, "null");

    private final TokenType type;
    private final String data;

    /**
     * Instantiates a new TokenImpl.
     *
     * @param type the token type
     * @param data the token data
     */
    private TokenImpl(TokenType type, String data) {
        this.type = Objects.requireNonNull(type);
        this.data = Objects.requireNonNull(data);
    }

    /**
     * Gets the token type.
     *
     * @return the token type
     */
    @Override
    public TokenType type() {
        return type;
    }

    /**
     * Gets the boolean value of the token.
     *
     * @return the boolean value
     * @throws JasonerException if the token type is not TRUE or FALSE
     */
    @Override
    public Boolean booleanValue() {
        if (type == TokenType.TRUE)
            return Boolean.TRUE;
        if (type == TokenType.FALSE)
            return Boolean.FALSE;
        throw new JasonerException("Invalid value for token type: " + type);
    }

    /**
     * Gets the string value of the token.
     *
     * @return the string value
     */
    @Override
    public String stringValue() {
        return data;
    }

    /**
     * Gets the numeric value of the token.
     *
     * @return the numeric value
     */
    @Override
    public BigDecimal numericValue() {
        return getPowerDelimiter().map((Integer pos) ->
                new BigDecimal(data.substring(0, pos)).pow(Integer.parseInt(data.substring(pos + 1))))
                .orElseGet(() -> new BigDecimal(data));
    }

    private Optional<Integer> getPowerDelimiter() {
        int pos = data.toLowerCase().indexOf("e");
        if (pos == -1) {
            return Optional.empty();
        }
        return Optional.of(pos);
    }

    /**
     * Creates a boolean token.
     *
     * @param value the boolean value
     * @return the boolean token
     */
    static Token createBooleanToken(String value) {
        return new TokenImpl(value.equals("true") ? TokenType.TRUE : TokenType.FALSE, value);
    }

    /**
     * Creates a null token.
     *
     * @return the null token
     */
    static Token createNullToken() {
        return new TokenImpl(TokenType.NULL, "null");
    }

    /**
     * Creates a number token.
     *
     * @param value the numeric value
     * @return the number token
     */
    static Token createNumberToken(String value) {
        return new TokenImpl(TokenType.NUMBER, value);
    }

    /**
     * Creates a text token.
     *
     * @param value the text value
     * @return the text token
     */
    static Token createTextToken(String value) {
        return new TokenImpl(TokenType.TEXT, value);
    }

    /**
     * Gets the string representation of the token.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return stringValue();
    }

    /**
     * Gets the value of the token as the specified type.
     *
     * @param ctype the class of the type
     * @return the value as the specified type
     * @throws JasonerException if the value cannot be converted to the specified type
     */
    @Override
    public Object typeValue(Class<?> ctype) {
        if (type() == TokenType.NULL) {
            return null;
        }
        if (ctype == null || Map.class.isAssignableFrom(ctype)) {
            return value();
        }
        if (ctype.isEnum()) {
            Enum<?>[] enums = ctype.asSubclass(Enum.class).getEnumConstants();
            for (Enum<?> e : enums) {
                if (e.name().equals(data)) {
                    return e;
                }
            }
            throw new JasonerException("Not found enum for: " + data);
        }
        String name = ctype.getSimpleName();
        return switch (name) {
            case "int", "Integer" -> Integer.parseInt(data);
            case "long", "Long" -> Long.parseLong(data);
            case "float", "Float" -> Float.parseFloat(data);
            case "double", "Double" -> Double.parseDouble(data);
            case "String" -> data;
            case "BigDecimal" -> numericValue();
            case "boolean", "Boolean" -> Boolean.parseBoolean(data);
            default -> value();
        };
    }

    /**
     * Gets the value of the token.
     *
     * @return the value
     */
    @Override
    public Object value() {
        if (type == TokenType.FALSE || type == TokenType.TRUE) {
            return booleanValue();
        }
        if (type == TokenType.NULL) {
            return null;
        }
        if (type == TokenType.NUMBER) {
            return numericValue();
        }
        return data;
    }
}