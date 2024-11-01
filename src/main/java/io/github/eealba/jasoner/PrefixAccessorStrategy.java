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
package io.github.eealba.jasoner;

import java.util.Objects;

/**
 * The enum Prefix accessor strategy.
 * This enum is used to define the prefix accessor strategy for the Jasoner library.
 * @author Edgar Alba
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
