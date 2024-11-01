package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.SerializationStrategy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Helper {
    static final Jasoner PRIVATE_JASONER = JasonerBuilder.create(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    static final JsonSerializer PRIVATE_JSON_SERIALIZER = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());
    static final JsonSerializer PRIVATE_SNAKE_CASE_JSON_SERIALIZER = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.SNAKE_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());
    static final JsonSerializer PRIVATE_PRETTY_JSON_SERIALIZER = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PRIVATE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(true)
            .build());
    static final JsonSerializer PROTECTED_JSON_SERIALIZER = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PROTECTED)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    static final JsonSerializer PACKAGE_JSON_SERIALIZER = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PACKAGE)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    static final JsonSerializer PUBLIC_JSON_SERIALIZER = new JsonSerializerImpl(new JasonerConfig.Builder()
            .serializationStrategy(SerializationStrategy.METHOD)
            .modifierStrategy(ModifierStrategy.PUBLIC)
            .namingStrategy(NamingStrategy.CAMEL_CASE)
            .removePrefixAccessors(true)
            .pretty(false)
            .build());

    static String readResource(String name) throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource(name).toURI());
        return Files.readString(path);
    }

}
