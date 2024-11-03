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
 * Annotation to mark fields or methods to be ignored during JSON serialization and deserialization.
 * <p>
 * Fields or methods annotated with {@code @JasonerTransient} will not be included in the JSON output
 * and will not be populated from the JSON input.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * public class User {
 *     private String name;
 *     private int age;
 *
 *     @JasonerTransient
 *     private String password;
 *
 *     // Getters and setters
 * }
 * }
 * </pre>
 * </p>
 *
 * @since 1.0
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface JasonerTransient {
}