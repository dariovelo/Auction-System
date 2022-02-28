package com.auctionhouse.pojo;

import com.auctionhouse.pojo.notifications.ClientMessagePayload;
import com.auctionhouse.pojo.notifications.Notification;

/**
 * Class to represent the client.
 */
public class Client {
    private static final String CL_COLOR = "\033[0;34m";
    private static final String CL_COLOR_WON = "\033[0;32m";
    private static final String CL_COLOR_NOT_SOLD = "\033[0;31m";
    private static final String RESET_COLOR = "\033[0m";

    private int id;
    private String nume;
    private String adresa;
    private int nrParticipari;
    private int nrLicitatiiCastigate;

    /**
     * Constructor to instantiate a client.
     *
     * @param id      the client id
     * @param nume    the name
     * @param adresa the address
     */
    public Client(int id, String nume, String adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.nrParticipari = 0;
        this.nrLicitatiiCastigate = 0;
    }

    /**
     * Method to get the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the name.
     *
     * @return the name
     */
    public String getNume() {
        return nume;
    }

    /**
     * Method to set the name.
     *
     * @param nume the name
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Method to get the address.
     *
     * @return the address
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Method to set the address.
     *
     * @param adresa the address
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * Method to get the number of participations.
     *
     * @return the number of participations.
     */
    public int getNrParticipari() {
        return nrParticipari;
    }

    /**
     * Method to set the number of participations.
     *
     * @param nrParticipari the number of participations
     */
    public void setNrParticipari(int nrParticipari) {
        this.nrParticipari = nrParticipari;
    }

    /**
     * Method to get the number of auctions won.
     *
     * @return the number auctions won
     */
    public int getNrLicitatiiCastigate() {
        return nrLicitatiiCastigate;
    }

    /**
     * Method to set the number of auctions won.
     *
     * @param nrLicitatiiCastigate the number of auctions won
     */
    public void setNrLicitatiiCastigate(int nrLicitatiiCastigate) {
        this.nrLicitatiiCastigate = nrLicitatiiCastigate;
    }

    /**
     * Method to send notifications to the client.
     *
     * @param notification the notification to send
     */
    public void sendNotification(Notification<ClientMessagePayload> notification) {
        ClientMessagePayload payload = notification.getContent();

        // If final result, increment the participation count
        if (payload.isFinalResult()) {
            this.nrParticipari++;
        }

        if (isProductNotSold(payload)) {
            // If the product is not sold, print the message saying product not sold
            System.out.println(CL_COLOR_NOT_SOLD + "[Client: " + this.id + "] Message Received [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       "Product: " + payload.getProductId() + " NOT SOLD as the minimum price for the product is not met in the bids" + RESET_COLOR);
            return;
        } else {
            // Print the notification content of the auction step with max bid details
            System.out.println(CL_COLOR + "[Client: " + this.id + "] Message Received [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       " current max bid: " + payload.getMaxBid() + " by client: " + payload.getClientIdWon() + RESET_COLOR);
        }

        // If the final result is won by the this client, message is printed saying it is won by this client.
        if (payload.isFinalResult() && this.id == payload.getClientIdWon()) {
            this.nrLicitatiiCastigate++;
            System.out.println(CL_COLOR_WON + "[Client: " + this.id + "] Message Received [Auction: " + payload.getAuctionId() + "] [Step: " + (payload.isFinalResult() ? "FINAL" : payload.getStepNumber()) + "] " +
                                       "Client: " + this.id + " has won the auction: " + payload.getAuctionId() + " and product: " + payload.getProductId() + RESET_COLOR);
        }
    }

    /**
     * Method to check whether the product is not sold.
     *
     * @param payload the message payload
     * @return true if final result of the auction and does not contain client id and max bid, else false
     */
    private boolean isProductNotSold(ClientMessagePayload payload) {
        return payload.isFinalResult() && payload.getClientIdWon() == null && payload.getMaxBid() == null;
    }
}
