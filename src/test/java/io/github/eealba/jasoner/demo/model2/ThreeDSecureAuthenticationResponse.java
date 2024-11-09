package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerProperty;

public class ThreeDSecureAuthenticationResponse {

    @JasonerProperty("authentication_status")
    private final ParesStatus authenticationStatus;
    @JasonerProperty("enrollment_status")
    private final Enrolled enrollmentStatus;

    private ThreeDSecureAuthenticationResponse(Builder builder) {
        authenticationStatus = builder.authenticationStatus;
        enrollmentStatus = builder.enrollmentStatus;

    }

    @JasonerProperty("authentication_status")
    public ParesStatus authenticationStatus() {
        return authenticationStatus;
    }

    @JasonerProperty("enrollment_status")
    public Enrolled enrollmentStatus() {
        return enrollmentStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ParesStatus authenticationStatus;
        private Enrolled enrollmentStatus;

        @JasonerProperty("authentication_status")
        public Builder authenticationStatus(ParesStatus value) {
            authenticationStatus = value;
            return this;
        }

        @JasonerProperty("enrollment_status")
        public Builder enrollmentStatus(Enrolled value) {
            enrollmentStatus = value;
            return this;
        }

        public ThreeDSecureAuthenticationResponse build() {
            return new ThreeDSecureAuthenticationResponse(this);
        }

    }

}

