package io.github.eealba.jasoner.demo.model2;


import io.github.eealba.jasoner.JasonerProperty;

public class AuthenticationResponse {

    @JasonerProperty("liability_shift")
    private final LiabilityShift liabilityShift;
    @JasonerProperty("three_d_secure")
    private final ThreeDSecureAuthenticationResponse threeDSecure;

    private AuthenticationResponse(Builder builder) {
        liabilityShift = builder.liabilityShift;
        threeDSecure = builder.threeDSecure;

    }

    @JasonerProperty("liability_shift")
    public LiabilityShift liabilityShift() {
        return liabilityShift;
    }

    @JasonerProperty("three_d_secure")
    public ThreeDSecureAuthenticationResponse threeDSecure() {
        return threeDSecure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private LiabilityShift liabilityShift;
        private ThreeDSecureAuthenticationResponse threeDSecure;

        @JasonerProperty("liability_shift")
        public Builder liabilityShift(LiabilityShift value) {
            liabilityShift = value;
            return this;
        }

        @JasonerProperty("three_d_secure")
        public Builder threeDSecure(ThreeDSecureAuthenticationResponse value) {
            threeDSecure = value;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }

    }

}

