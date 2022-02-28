package com.auctionhouse.pojo.notifications;

/**
 * Interface to represent the notification
 *
 * @param <T> the type of the content
 */
public interface Notification<T> {
    /**
     * Method to get the content of the notification.
     *
     * @return the content of the notification
     */
    T getContent();
}
