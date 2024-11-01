package io.github.eealba.jasoner.demo.model2;



/**
 * The cancel subscription request details.
 */
public record SubscriptionCancelRequest(String reason) {

    public SubscriptionCancelRequest(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Field reason can`t be null");
        }
        this.reason = reason;
    }

}
