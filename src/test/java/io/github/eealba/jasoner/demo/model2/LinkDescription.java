package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class LinkDescription {

    
    private final String href;
    
    private final String rel;
    
    private final Method method;

    private LinkDescription(Builder builder) {
        method = builder.method;
        href = Objects.requireNonNull(builder.href);
        rel = Objects.requireNonNull(builder.rel);
    }

    
    public String href() {
        return href;
    }

    
    public String rel() {
        return rel;
    }

    
    public Method method() {
        return method;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String href;
        private String rel;
        private Method method;

        
        public Builder href(String value) {
            href = value;
            return this;
        }

        
        public Builder rel(String value) {
            rel = value;
            return this;
        }

        
        public Builder method(Method value) {
            method = value;
            return this;
        }

        public LinkDescription build() {
            return new LinkDescription(this);
        }

    }
    /**
     * The HTTP method required to make the related call.
     */
    public enum Method {
        GET,
        POST,
        PUT,
        DELETE,
        HEAD,
        CONNECT,
        OPTIONS,
        PATCH
    }
}

