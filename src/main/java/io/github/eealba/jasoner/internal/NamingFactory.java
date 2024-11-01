package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.NamingStrategy;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class NamingFactory {
    private static final Function<String, String> CAMEL_CASE_TO_SNAKE_CASE =
            str -> str.replaceAll("([a-z0-9])([A-Z])", "$1_$2").toLowerCase();
    private static final Function<String, String> CAMEL_CASE_TO_KEBAB_CASE =
            str -> str.replaceAll("([a-z0-9])([A-Z])", "$1-$2").toLowerCase();
    private static final Function<String, String> KEBAB_CASE_TO_SNAKE_CASE =
            str -> str.replaceAll("-", "_");
    private static final Function<String, String> SNAKE_CASE_TO_KEBAB_CASE =
            str -> str.replaceAll("_", "-");

    private NamingFactory(){

    }
    static Naming get(NamingStrategy strategy){
        switch (strategy) {
            case LOWER_CASE:
                return (String::toLowerCase);
            case UPPER_CASE:
                return (String::toUpperCase);
            case SNAKE_CASE:
                return CAMEL_CASE_TO_SNAKE_CASE.andThen(KEBAB_CASE_TO_SNAKE_CASE)::apply;
            case UPPER_SNAKE_CASE:
                return CAMEL_CASE_TO_SNAKE_CASE.andThen(KEBAB_CASE_TO_SNAKE_CASE).andThen(String::toUpperCase)::apply;
            case KEBAB_CASE:
                return CAMEL_CASE_TO_KEBAB_CASE.andThen(SNAKE_CASE_TO_KEBAB_CASE)::apply;
            case UPPER_KEBAB_CASE:
                return CAMEL_CASE_TO_KEBAB_CASE.andThen(SNAKE_CASE_TO_KEBAB_CASE).andThen(String::toUpperCase)::apply;
            case CAMEL_CASE:
                return NamingFactory::camelCase;
            default:
                return (src -> src);
        }
    }

    private static String camelCase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (!str.contains("_") && !str.contains("-")) {
            if (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
                return str.substring(0,1).toLowerCase() + str.substring(1);
            }
            return str;
        }

        str = str.toLowerCase(); // Convert the entire string to lowercase first
        Pattern pattern = Pattern.compile("([_\\-])([a-zA-Z])");
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
