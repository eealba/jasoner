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

/**
 * The enum Naming strategy.
 * This enum is used to define the naming strategy for the Jasoner library.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
public enum NamingStrategy {
    /**
     * No naming strategy.
     */
    NONE,
    /**
     * Lower case naming strategy.
     */
    LOWER_CASE,
    /**
     * Upper case naming strategy.
     */
    UPPER_CASE,
    /**
     * Snake case naming strategy.
     */
    SNAKE_CASE,
    /**
     * Upper snake case naming strategy.
     */
    UPPER_SNAKE_CASE,
    /**
     * Kebab case naming strategy.
     */
    KEBAB_CASE,
    /**
     * Upper kebab case naming strategy.
     */
    UPPER_KEBAB_CASE,
    /**
     * Camel case naming strategy.
     */
    CAMEL_CASE
}