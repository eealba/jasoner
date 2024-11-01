package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Token.
 */
class TokenImpl implements Token {
    /**
     * The Object start.
     */
    static final Token OBJECT_START = new TokenImpl(TokenType.OBJECT_START, "{");
    /**
     * The Object end.
     */
    static final Token OBJECT_END = new TokenImpl(TokenType.OBJECT_END, "}");
    /**
     * The Array start.
     */
    static final Token ARRAY_START = new TokenImpl(TokenType.ARRAY_START, "[");
    /**
     * The Array end.
     */
    static final Token ARRAY_END = new TokenImpl(TokenType.ARRAY_END, "]");
    /**
     * The Colon.
     */
    static final Token COLON = new TokenImpl(TokenType.COLON, ":");
    /**
     * The Comma.
     */
    static final Token COMMA = new TokenImpl(TokenType.COMMA, ",");
    /**
     * The False.
     */
    static final Token FALSE = new TokenImpl(TokenType.FALSE, "false");
    /**
     * The True.
     */
    static final Token TRUE = new TokenImpl(TokenType.TRUE, "true");
    /**
     * The Null.
     */
    static final Token NULL = new TokenImpl(TokenType.NULL, "null");

	private final TokenType type;
	private final String data;

	private TokenImpl(TokenType type, String data) {
		this.type = Objects.requireNonNull(type);
		this.data = Objects.requireNonNull(data);
	}

	@Override
	public TokenType type() {
		return type;
	}

	@Override
	public Boolean booleanValue() {
		if (type == TokenType.TRUE)
			return Boolean.TRUE;
		if (type == TokenType.FALSE)
			return Boolean.FALSE;
		throw new JasonerException("Invalid value for token type: " + type);
	}

	@Override
	public String stringValue() {
		return data;
	}

	@Override
	public BigDecimal numericValue() {
		return getPowerDelimiter().map((Integer pos ) ->
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
	 * Create boolean token token.
	 *
	 * @param value the value
	 * @return the token
	 */
	static Token createBooleanToken(String value) {
		return new TokenImpl(value.equals("true") ? TokenType.TRUE : TokenType.FALSE, value);
	}

	/**
	 * Create null token token.
	 *
	 * @return the token
	 */
	static Token createNullToken() {
		return new TokenImpl(TokenType.NULL, "null");
	}

    /**
     * Create number token token.
     *
     * @param value the value
     * @return the token
     */
    static Token createNumberToken(String value) {
		return new TokenImpl(TokenType.NUMBER, value);
	}

    /**
     * Create text token token.
     *
     * @param value the value
     * @return the token
     */
    static Token createTextToken(String value) {
		return new TokenImpl(TokenType.TEXT, value);
	}

	@Override
	public String toString() {
		return stringValue();
	}

	@Override
	public Object typeValue(Class<?> ctype) {
		if (type() == TokenType.NULL) {
			return null;
		}
		if (ctype == null) {
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
		switch (name) {
		case "int":
		case "Integer":
			return Integer.parseInt(data);
		case "long":
		case "Long":
			return Long.parseLong(data);
		case "float":
		case "Float":
			return Float.parseFloat(data);
		case "double":
		case "Double":
			return Double.parseDouble(data);
		case "String":
			return data;
		case "BigDecimal":
			return numericValue();
		case "boolean":
		case "Boolean":
			return Boolean.parseBoolean(data);

		default:
			break;
		}
		return data;
	}

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
