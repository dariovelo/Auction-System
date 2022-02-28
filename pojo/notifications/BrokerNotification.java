package com.auctionhouse.pojo.notifications;

/**
 * Class to represent the broker notification.
 */
public class BrokerNotification implements Notification<BrokerMessagePayload> {
    private BrokerMessagePayload content;

    public void setContent(BrokerMessagePayload content) {
        this.content = content;
    }

    /**
     * Constructor to instantiate the BrokerNotification.
     *
     * @param content the content of the notification
     */
    public BrokerNotification(BrokerMessagePayload content) {
        this.content = content;
    }

    /**
     * Method to get the content of the notification.
     *
     * @return the content of the notification
     */
    @Override
    public BrokerMessagePayload getContent() {
        return content;
    }
}
