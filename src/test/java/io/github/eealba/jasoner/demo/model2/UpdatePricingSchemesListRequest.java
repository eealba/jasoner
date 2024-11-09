package io.github.eealba.jasoner.demo.model2;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The update pricing scheme request details.
 */
public record UpdatePricingSchemesListRequest(@JasonerProperty("pricing_schemes") List<UpdatePricingSchemeRequest> pricingSchemes) {

    public UpdatePricingSchemesListRequest(List<UpdatePricingSchemeRequest> pricingSchemes) {
        if (pricingSchemes == null) {
            throw new IllegalArgumentException("Field pricingSchemes can`t be null");
        }
        this.pricingSchemes = pricingSchemes;
    }

}
