package io.github.eealba.jasoner.demo.model1;

import lombok.Data;

@Data
public class PaymentPreferences {
    Boolean autoBillOutstanding;
    Money setupFee;
    String setupFeeFailureAction;
    Integer paymentFailureThreshold;
}
