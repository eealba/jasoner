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

import io.github.eealba.jasoner.NamingStrategy;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class NamingFactory.
 * This class provides factory methods to get Naming implementations based on different naming strategies.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class NamingFactory {
    private static final Function<String, String> CAMEL_CASE_TO_SNAKE_CASE =
            str -> str.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase();
    private static final Function<String, String> CAMEL_CASE_TO_KEBAB_CASE =
            str -> str.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase();
    private static final Function<String, String> KEBAB_CASE_TO_SNAKE_CASE =
            str -> str.replaceAll("-", "_");
    private static final Function<String, String> SNAKE_CASE_TO_KEBAB_CASE =
            str -> str.replaceAll("_", "-");

    private NamingFactory() {
    }

    /**
     * Gets a Naming implementation based on the provided naming strategy.
     *
     * @param strategy the naming strategy
     * @return the Naming implementation
     */
    static Naming get(NamingStrategy strategy) {
        return switch (strategy) {
            case LOWER_CASE -> (String::toLowerCase);
            case UPPER_CASE -> (String::toUpperCase);
            case SNAKE_CASE -> CAMEL_CASE_TO_SNAKE_CASE.andThen(KEBAB_CASE_TO_SNAKE_CASE)::apply;
            case UPPER_SNAKE_CASE ->
                    CAMEL_CASE_TO_SNAKE_CASE.andThen(KEBAB_CASE_TO_SNAKE_CASE).andThen(String::toUpperCase)::apply;
            case KEBAB_CASE -> CAMEL_CASE_TO_KEBAB_CASE.andThen(SNAKE_CASE_TO_KEBAB_CASE)::apply;
            case UPPER_KEBAB_CASE ->
                    CAMEL_CASE_TO_KEBAB_CASE.andThen(SNAKE_CASE_TO_KEBAB_CASE).andThen(String::toUpperCase)::apply;
            case CAMEL_CASE -> NamingFactory::camelCase;
            default -> (src -> src);
        };
    }

    /**
     * Converts a string to camel case.
     *
     * @param str the string to convert
     * @return the camel case string
     */
    private static String camelCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (!str.contains("_") && !str.contains("-")) {
            if (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
                return str.substring(0, 1).toLowerCase() + str.substring(1);
            }
            return str;
        }

        str = str.toLowerCase(); // Convert the entire string to lowercase first
        Pattern pattern = Pattern.compile("([_\\-])([a-zA-Z0-9])");
        Matcher matcher = pattern.matcher(str);
        StringBuilder camelCase = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(camelCase, matcher.group(2).toUpperCase());
        }
        matcher.appendTail(camelCase);

        if (!camelCase.isEmpty()) {
            camelCase.setCharAt(0, Character.toLowerCase(camelCase.charAt(0)));
        }
        return camelCase.toString();
    }
}