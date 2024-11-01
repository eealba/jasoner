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

/**
 * The enum Token type.
 * This enum is used to define the type of token.
 * @author Edgar Alba
 */
enum TokenType {
    /**
     * Object start token type.
     */
    OBJECT_START,
    /**
     * Object end token type.
     */
    OBJECT_END,
    /**
     * Array start token type.
     */
    ARRAY_START,
    /**
     * Array end token type.
     */
    ARRAY_END,
    /**
     * True token type.
     */
    TRUE,
    /**
     * False token type.
     */
    FALSE,
    /**
     * Null token type.
     */
    NULL,
    /**
     * Comma token type.
     */
    COMMA,
    /**
     * Colon token type.
     */
    COLON,
    /**
     * Text token type.
     */
    TEXT,
    /**
     * Number token type.
     */
    NUMBER

}
