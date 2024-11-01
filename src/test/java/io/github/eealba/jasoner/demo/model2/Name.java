package io.github.eealba.jasoner.demo.model2;



public class Name {

    private final String prefix;
    private final String givenName;
    private final String surname;
    private final String middleName;
    private final String suffix;
    private final String alternateFullName;
    private final String fullName;

    private Name(Builder builder) {
        prefix = builder.prefix;
        givenName = builder.givenName;
        surname = builder.surname;
        middleName = builder.middleName;
        suffix = builder.suffix;
        alternateFullName = builder.alternateFullName;
        fullName = builder.fullName;

    }

    public String prefix() {
        return prefix;
    }

    public String givenName() {
        return givenName;
    }

    public String surname() {
        return surname;
    }

    public String middleName() {
        return middleName;
    }

    public String suffix() {
        return suffix;
    }

    public String alternateFullName() {
        return alternateFullName;
    }

    public String fullName() {
        return fullName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String prefix;
        private String givenName;
        private String surname;
        private String middleName;
        private String suffix;
        private String alternateFullName;
        private String fullName;

        public Builder prefix(String value) {
            prefix = value;
            return this;
        }

        public Builder givenName(String value) {
            givenName = value;
            return this;
        }

        public Builder surname(String value) {
            surname = value;
            return this;
        }

        public Builder middleName(String value) {
            middleName = value;
            return this;
        }

        public Builder suffix(String value) {
            suffix = value;
            return this;
        }

        public Builder alternateFullName(String value) {
            alternateFullName = value;
            return this;
        }

        public Builder fullName(String value) {
            fullName = value;
            return this;
        }

        public Name build() {
            return new Name(this);
        }

    }

}

