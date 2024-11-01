package io.github.eealba.jasoner.demo.model2;



public class PaymentMethod {

    private final String payerSelected;
    private final PayeePaymentMethodPreference payeePreferred;
    private final StandardEntryClassCode standardEntryClassCode;

    private PaymentMethod(Builder builder) {
        payerSelected = builder.payerSelected;
        payeePreferred = builder.payeePreferred;
        standardEntryClassCode = builder.standardEntryClassCode;

    }

    public String payerSelected() {
        return payerSelected;
    }

    public PayeePaymentMethodPreference payeePreferred() {
        return payeePreferred;
    }

    public StandardEntryClassCode standardEntryClassCode() {
        return standardEntryClassCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String payerSelected;
        private PayeePaymentMethodPreference payeePreferred;
        private StandardEntryClassCode standardEntryClassCode;

        public Builder payerSelected(String value) {
            payerSelected = value;
            return this;
        }

        public Builder payeePreferred(PayeePaymentMethodPreference value) {
            payeePreferred = value;
            return this;
        }

        public Builder standardEntryClassCode(StandardEntryClassCode value) {
            standardEntryClassCode = value;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }

    }
    /**
     * NACHA (the regulatory body governing the ACH network) requires that API callers (merchants, partners) obtain the consumer’s explicit authorization before initiating a transaction. To stay compliant, you’ll need to make sure that you retain a compliant authorization for each transaction that you originate to the ACH Network using this API. ACH transactions are categorized (using SEC codes) by how you capture authorization from the Receiver (the person whose bank account is being debited or credited). PayPal supports the following SEC codes.
     */
    public enum StandardEntryClassCode {
        TEL,
        WEB,
        CCD,
        PPD
    }
}

