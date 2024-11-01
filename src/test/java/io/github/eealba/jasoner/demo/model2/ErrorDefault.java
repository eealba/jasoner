package io.github.eealba.jasoner.demo.model2;

import java.util.List;

public class ErrorDefault {

    private final String name;
    private final String message;
    private final List<ErrorDetails> issues;
    private final String debugId;
    private final String informationLink;

    private ErrorDefault(Builder builder) {
        name = builder.name;
        message = builder.message;
        issues = builder.issues;
        debugId = builder.debugId;
        informationLink = builder.informationLink;

    }

    public String name() {
        return name;
    }

    public String message() {
        return message;
    }

    public List<ErrorDetails> issues() {
        return issues;
    }

    public String debugId() {
        return debugId;
    }

    public String informationLink() {
        return informationLink;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String message;
        private List<ErrorDetails> issues;
        private String debugId;
        private String informationLink;

        public Builder name(String value) {
            name = value;
            return this;
        }

        public Builder message(String value) {
            message = value;
            return this;
        }

        public Builder issues(List<ErrorDetails> value) {
            issues = value;
            return this;
        }

        public Builder debugId(String value) {
            debugId = value;
            return this;
        }

        public Builder informationLink(String value) {
            informationLink = value;
            return this;
        }

        public ErrorDefault build() {
            return new ErrorDefault(this);
        }

    }

}

