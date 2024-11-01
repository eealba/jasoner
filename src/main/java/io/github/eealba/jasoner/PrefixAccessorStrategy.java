package io.github.eealba.jasoner;

import java.util.Objects;

/**
 * The enum Prefix accessor strategy.
 */
public enum PrefixAccessorStrategy {
    /**
     * None prefix accessor strategy.
     */
    NONE,
    /**
     * Remove prefix accessor strategy.
     */
    REMOVE;

    /**
     * Remove prefix string.
     *
     * @param name the name
     * @return the string
     */
    public String removePrefix(String name) {
		Objects.requireNonNull(name);
		if (this == REMOVE) {
			String lowercase = name.toLowerCase();
			if (lowercase.startsWith("is")) {
				return name.substring(2);
			}
			if (lowercase.startsWith("has") || lowercase.startsWith("get")) {
				return name.substring(3);
			}

		}
		return name;
	}
}
