package io.github.eealba.example;

import io.github.eealba.jasoner.JasonerProperty;
/**
 * The location of the field that caused the error. Value is `body`, `path`, or `query`.
 */
public enum ErrorLocation {
    @JasonerProperty("body")
    BODY,
    @JasonerProperty("path")
    PATH,
    @JasonerProperty("query")
    QUERY
}
