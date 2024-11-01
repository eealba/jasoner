package io.github.eealba.jasoner.demo.model2;



public class AuthenticationResponse {

    private final LiabilityShift liabilityShift;
    private final ThreeDSecureAuthenticationResponse threeDSecure;

    private AuthenticationResponse(Builder builder) {
        liabilityShift = builder.liabilityShift;
        threeDSecure = builder.threeDSecure;

    }

    public LiabilityShift liabilityShift() {
        return liabilityShift;
    }

    public ThreeDSecureAuthenticationResponse threeDSecure() {
        return threeDSecure;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private LiabilityShift liabilityShift;
        private ThreeDSecureAuthenticationResponse threeDSecure;

        public Builder liabilityShift(LiabilityShift value) {
            liabilityShift = value;
            return this;
        }

        public Builder threeDSecure(ThreeDSecureAuthenticationResponse value) {
            threeDSecure = value;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this);
        }

    }

}

