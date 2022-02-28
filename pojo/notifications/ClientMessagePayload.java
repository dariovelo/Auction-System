package com.auctionhouse.pojo.notifications;

/**
 * Class to represent the ClientMessagePayload.
 */
public class ClientMessagePayload {
    private int auctionId;
    private int productId;
    private boolean isFinalResult;
    private Integer clientIdWon;
    private Double maxBid;
    private Integer stepNumber;

    /**
     * Constructor to instantiate the ClientMessagePayload.
     *
     * @param auctionId     the auction id
     * @param productId     the product id
     * @param isFinalResult true if final result of auction, else false
     * @param clientIdWon   the client id who won in step
     * @param maxBid        the max bid amount
     * @param stepNumber    the step number of the auction
     */
    public ClientMessagePayload(int auctionId, int productId, boolean isFinalResult, Integer clientIdWon, Double maxBid, Integer stepNumber) {
        this.auctionId = auctionId;
        this.productId = productId;
        this.isFinalResult = isFinalResult;
        this.clientIdWon = clientIdWon;
        this.maxBid = maxBid;
        this.stepNumber = stepNumber;
    }

    /**
     * Method to get the auction id.
     *
     * @return the auction id
     */
    public int getAuctionId() {
        return auctionId;
    }

    /**
     * Method to get the product id.
     *
     * @return the product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Method to get the message is regarding the final result of the auction.
     *
     * @return true if final result of the auction, else false
     */
    public boolean isFinalResult() {
        return isFinalResult;
    }

    /**
     * Method to get the client id won.
     *
     * @return the client id won
     */
    public Integer getClientIdWon() {
        return clientIdWon;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setFinalResult(boolean finalResult) {
        isFinalResult = finalResult;
    }

    public void setClientIdWon(Integer clientIdWon) {
        this.clientIdWon = clientIdWon;
    }

    public void setMaxBid(Double maxBid) {
        this.maxBid = maxBid;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    /**
     * Method to get the max bid amount.
     *
     * @return the max bid amount
     */
    public Double getMaxBid() {
        return maxBid;
    }

    /**
     * Method to get the step number.
     *
     * @return the step number
     */
    public Integer getStepNumber() {
        return stepNumber;
    }
}
