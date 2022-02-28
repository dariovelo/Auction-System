package com.auctionhouse.pojo.notifications;

/**
 * Class to represent the client notification.
 */
public class ClientNotification implements Notification<ClientMessagePayload> {
    private ClientMessagePayload content;

    /**
     * Constructor to instantiate the client notification.
     *
     * @param content the content of the notification
     */
    public ClientNotification(ClientMessagePayload content) {
        this.content = content;
    }

    /**
     * Method to get the content of the notification.
     *
     * @return the content
     */
    @Override
    public ClientMessagePayload getContent() {
        return content;
    }
}
