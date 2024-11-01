package io.github.eealba.jasoner.demo.model2;



/**
 * The charge amount from the subscriber.
 */
public record SubscriptionCaptureRequest(String note, String captureType, Money amount) {

    public SubscriptionCaptureRequest(String note, String captureType, Money amount) {
        if (note == null) {
            throw new IllegalArgumentException("Field note can`t be null");
        }
        if (captureType == null) {
            throw new IllegalArgumentException("Field captureType can`t be null");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Field amount can`t be null");
        }
        if (!captureType.matches("^[A-Z_]+$")) {
            throw new IllegalArgumentException("Invalid pattern for field captureType");
        }
        this.note = note;
        this.captureType = captureType;
        this.amount = amount;
    }

}
