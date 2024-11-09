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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation JasonerProperty.
 * This annotation is used to specify a custom property name for fields or methods
 * when serializing or deserializing JSON objects using the Jasoner library.
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * public class Person {
 *     @JasonerProperty("full_name")
 *     private String name;
 *
 *     @JasonerProperty("age_years")
 *     private int age;
 *
 *     // Getters and setters
 * }
 * }
 * </pre>
 *
 * @since 1.0
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
public @interface JasonerProperty {
    /**
     * Specifies the custom property name to be used during JSON serialization and deserialization.
     *
     * @return the custom property name
     */
    String value();
}