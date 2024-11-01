package io.github.eealba.jasoner.demo.model2;



public class ThreeDSecureAuthenticationResponse {

    private final ParesStatus authenticationStatus;
    private final Enrolled enrollmentStatus;

    private ThreeDSecureAuthenticationResponse(Builder builder) {
        authenticationStatus = builder.authenticationStatus;
        enrollmentStatus = builder.enrollmentStatus;

    }

    public ParesStatus authenticationStatus() {
        return authenticationStatus;
    }

    public Enrolled enrollmentStatus() {
        return enrollmentStatus;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ParesStatus authenticationStatus;
        private Enrolled enrollmentStatus;

        public Builder authenticationStatus(ParesStatus value) {
            authenticationStatus = value;
            return this;
        }

        public Builder enrollmentStatus(Enrolled value) {
            enrollmentStatus = value;
            return this;
        }

        public ThreeDSecureAuthenticationResponse build() {
            return new ThreeDSecureAuthenticationResponse(this);
        }

    }

}

