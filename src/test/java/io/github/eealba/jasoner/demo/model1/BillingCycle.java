package io.github.eealba.jasoner.demo.model1;

import lombok.Data;

@Data
public class BillingCycle {
    String tenureType;
    Integer sequence;
    Frequency frequency;
    Integer totalCycles;
    PricingScheme pricingScheme;

}
