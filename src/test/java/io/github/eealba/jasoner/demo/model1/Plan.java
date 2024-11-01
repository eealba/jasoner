package io.github.eealba.jasoner.demo.model1;

import lombok.Data;

import java.util.List;

@Data
public class Plan {
    String productId;
    String name;
    List<BillingCycle> billingCycles;
    PaymentPreferences payment_preferences;
    String description;
    String status;
    Taxes taxes;
}
