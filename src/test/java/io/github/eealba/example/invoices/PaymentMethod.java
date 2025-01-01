package io.github.eealba.example.invoices;
/**
 * The payment mode or method through which the invoicer can accept the payments.
 */
public enum PaymentMethod {
    BANK_TRANSFER,
    CASH,
    CHECK,
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    WIRE_TRANSFER,
    OTHER
}
