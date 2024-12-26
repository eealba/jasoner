package io.github.eealba.example;

import io.github.eealba.jasoner.JasonerProperty;


/**
 * Any additional payment instructions to be consider during payment processing. This processing instruction is applicable 
for Capturing an order or Authorizing an Order.
 */
public class PaymentInstruction {


    @JasonerProperty("payee_pricing_tier_id")
    private final String payeePricingTierId;
    @JasonerProperty("payee_receivable_fx_rate_id")
    private final String payeeReceivableFxRateId;

    private PaymentInstruction(Builder builder) {
        payeePricingTierId = builder.payeePricingTierId;
        payeeReceivableFxRateId = builder.payeeReceivableFxRateId;

    }

    /**
     * This field is only enabled for selected merchants/partners to use and provides the ability to trigger a 
specific pricing rate/plan for a payment transaction. The list of eligible 'payee_pricing_tier_id' would be 
provided to you by your Account Manager. Specifying values other than the one provided to you by your account 
manager would result in an error.
     */
    @JasonerProperty("payee_pricing_tier_id")
    public String payeePricingTierId() {
        return payeePricingTierId;
    }

    /**
     * FX identifier generated returned by PayPal to be used for payment processing in order to honor FX rate (for 
eligible integrations) to be used when amount is settled/received into the payee account.
     */
    @JasonerProperty("payee_receivable_fx_rate_id")
    public String payeeReceivableFxRateId() {
        return payeeReceivableFxRateId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String payeePricingTierId;
        private String payeeReceivableFxRateId;


        /**
         * This field is only enabled for selected merchants/partners to use and provides the ability to trigger a 
specific pricing rate/plan for a payment transaction. The list of eligible 'payee_pricing_tier_id' would be 
provided to you by your Account Manager. Specifying values other than the one provided to you by your account 
manager would result in an error.
         */
        @JasonerProperty("payee_pricing_tier_id")
        public Builder payeePricingTierId(String value) {
            this.payeePricingTierId = value;
            return this;
        }

        /**
         * FX identifier generated returned by PayPal to be used for payment processing in order to honor FX rate (for 
eligible integrations) to be used when amount is settled/received into the payee account.
         */
        @JasonerProperty("payee_receivable_fx_rate_id")
        public Builder payeeReceivableFxRateId(String value) {
            this.payeeReceivableFxRateId = value;
            return this;
        }

        public PaymentInstruction build() {
            return new PaymentInstruction(this);
        }

    }


}

