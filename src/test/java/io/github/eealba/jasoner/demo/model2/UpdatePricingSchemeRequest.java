package io.github.eealba.jasoner.demo.model2;



/**
 * The update pricing scheme request details.
 */
public record UpdatePricingSchemeRequest(Integer billingCycleSequence, PricingScheme pricingScheme) {

    public UpdatePricingSchemeRequest(Integer billingCycleSequence, PricingScheme pricingScheme) {
        if (billingCycleSequence == null) {
            throw new IllegalArgumentException("Field billingCycleSequence can`t be null");
        }
        if (pricingScheme == null) {
            throw new IllegalArgumentException("Field pricingScheme can`t be null");
        }
        this.billingCycleSequence = billingCycleSequence;
        this.pricingScheme = pricingScheme;
    }

}
