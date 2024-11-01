package io.github.eealba.jasoner.demo.model2;



/**
 * The suspend subscription request details.
 */
public record SubscriptionSuspendRequest(String reason) {

    public SubscriptionSuspendRequest(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Field reason can`t be null");
        }
        this.reason = reason;
    }

}
