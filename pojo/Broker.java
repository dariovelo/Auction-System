package com.auctionhouse.pojo;

import com.auctionhouse.CasaDeLicitatii;
import com.auctionhouse.pojo.notifications.BrokerMessagePayload;
import com.auctionhouse.pojo.notifications.ClientMessagePayload;
import com.auctionhouse.pojo.notifications.ClientNotification;
import com.auctionhouse.pojo.notifications.Notification;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to represent the broker.
 */
public class Broker extends Angajat {
    private List<Client> clienti = new LinkedList<>();

    /**
     * Constructor to instantiate the broker.
     *
     * @param id   the id
     * @param name the name
     */
    public Broker(int id, String name) {
        super(id, name);
    }

    /**
     * Method to get the clients.
     *
     * @return the clients
     */
    public List<Client> getClienti() {
        return clienti;
    }

    public void setClienti(List<Client> clienti) {
        this.clienti = clienti;
    }

    /**
     * Method to send the notification to the broker.
     *
     * @param notification the notification to be sent to the broker
     */
    public void sendNotification(Notification<BrokerMessagePayload> notification) {
        BrokerMessagePayload payload = notification.getContent();

        if (isProductNotSold(payload)) {
            // Print the received message that the product was not sold
            System.out.println("[Broker: " + this.id + "] Message Received [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       "Product: " + payload.getProductId() + " NOT SOLD as the minimum price for the product is not met in the bids");
        } else {
            // Print the received message about the auction bid amounts
            System.out.println("[Broker: " + this.id + "] Message Received [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       "max bid: " + payload.getMaxBid() + " by client: " + payload.getClientIdWon());

        }

        // Get the clients list related to the broker, which are in the current auction
        List<Client> clientsListToSendMessage = clienti.stream().filter(client -> payload.getClientIdListToSendMessage().contains(client.getId())).collect(Collectors.toList());

        // If there are no clients related to the broker, it means that broker's clients are not involved in the auction
        if (clientsListToSendMessage.isEmpty()) {
            System.out.println("[Broker: " + this.id + "] INFO [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       "Broker does not have clients related to the auction: " + payload.getAuctionId());
            return;
        }

        // Check whether the broker is the mediator of the winning client of the final auction result and print the details including commission
        if (payload.isFinalResult() && clientsListToSendMessage.stream().map(Client::getId).collect(Collectors.toSet()).contains(payload.getClientIdWon())) {
            System.out.println("[Broker: " + this.id + "] INFO [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       "Broker: " + this.id + " is the mediator of the winning client: " + payload.getClientIdWon() + " of product: " + payload.getProductId() + " with bid amount: " + payload.getMaxBid() + " and receives a commission of: " + payload.getCommission());
        }

        // Prepare the client notification to be sent
        ClientMessagePayload clientMessagePayload = new ClientMessagePayload(payload.getAuctionId(), payload.getProductId(), payload.isFinalResult(), payload.getClientIdWon(), payload.getMaxBid(), payload.getStepNumber());
        Notification<ClientMessagePayload> clientNotification = new ClientNotification(clientMessagePayload);

        System.out.println("[Broker: " + this.id + "] INFO [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                   "Sending messages to the clients: " + clientsListToSendMessage.stream().map(Client::getId).collect(Collectors.toList()) + " regarding the auction results");

        // Send notifications to the clients
        for (Client client : clientsListToSendMessage) {
            client.sendNotification(clientNotification);
        }
    }

    /**
     * Method to send the start notification.
     *
     * @param auctionId the auction id
     */
    public void sendStartNotification(int auctionId) {
        System.out.println("[Broker: " + this.id + "] Message Received [Auction: " + auctionId + "] " +
                                   "Auction started");
    }

    /**
     * Method to clean the sold products in the auction house.
     *
     * @param casaDeLicitatii the auction house
     */
    public void cleanSoldProductsInAuctionHouse(CasaDeLicitatii casaDeLicitatii) {
        casaDeLicitatii.stergeProduseVandute();
    }

    /**
     * Method to check whether the product is not sold.
     *
     * @param payload the message payload
     * @return true if final result of the auction and does not contain client id and max bid, else false
     */
    private boolean isProductNotSold(BrokerMessagePayload payload) {
        return payload.isFinalResult() && payload.getClientIdWon() == null && payload.getMaxBid() == null;
    }
}
