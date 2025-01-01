package io.github.eealba.example.invoices;
/**
 * The status of the invoice.
 */
public enum InvoiceStatus {
    DRAFT,
    SENT,
    SCHEDULED,
    PAID,
    MARKED_AS_PAID,
    CANCELLED,
    REFUNDED,
    PARTIALLY_PAID,
    PARTIALLY_REFUNDED,
    MARKED_AS_REFUNDED,
    UNPAID,
    PAYMENT_PENDING
}
