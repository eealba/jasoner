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

import java.math.BigDecimal;

/**
 * The interface Token.
 * This interface is used to represent a JSON token.
 * @author Edgar Alba
 */
interface Token {
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
