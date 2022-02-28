package com.auctionhouse.pojo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Class to represent the bid.
 */
public class Bid {
    private double stepWiseBidAmounts;
    private Client client;
    private List<Double> stepWiseLicitareMax;
    private Iterator<Double> BidIterator;

    /**
     * Method to get the maximum amount of the bid.
     *
     * @return the maximum amount of the bid
     */
    private double getStepWiseBidAmounts() {
        return stepWiseBidAmounts;
    }

    /**
     * Method to set the maximum amount of the bid.
     *
     * @param stepWiseBidAmounts the maximum amount of the bid
     */
    private void setStepWiseBidAmounts(double stepWiseBidAmounts) {
        this.stepWiseBidAmounts = stepWiseBidAmounts;
    }

    /**
     * Method to get the client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method to set the client.
     *
     * @param client the client
     */
    private void setClient(Client client) {
        this.client = client;
    }

    /**
     * Method to get the current bid.
     * <p>
     * The bid for each step will be returned by this method.
     * The algorithm used here is to iterate the step wise bid amounts and return.
     * </p>
     *
     * @return the current bid amount
     */
    public Double getCurrentBid() {
        if (BidIterator == null && stepWiseLicitareMax != null) {
            BidIterator = stepWiseLicitareMax.iterator();
        }

        if (BidIterator != null && BidIterator.hasNext()) {
            return BidIterator.next();
        }

        return null;
    }

    /**
     * Method to reset iterator.
     */
    public void resetIterator() {
        BidIterator = null;
    }

    /**
     * Method to get the step wise bid amounts.
     *
     * @return the step wise bid amount.
     */
    private List<Double> getStepWiseLicitareMax() {
        return stepWiseLicitareMax;
    }

    /**
     * Method to set the step wise bid amounts.
     *
     * @param stepWiseLicitareMax the step wise bid amounts
     */
    private void setStepWiseLicitareMax(Double... stepWiseLicitareMax) {
        this.stepWiseLicitareMax = Arrays.asList(stepWiseLicitareMax);
    }

    /**
     * Class to represent the bid builder.
     */
    public static class Builder {
        private double maxAmount;
        private Client client;
        private Double[] stepWiseBidAmounts;

        /**
         * Method to set the maximum amount.
         *
         * @param maxAmount the maximum amount
         * @return the builder
         */
        public Builder withMaxAmount(double maxAmount) {
            this.maxAmount = maxAmount;
            return this;
        }

        /**
         * Method to set the client.
         *
         * @param client the client
         * @return the builder
         */
        public Builder withClient(Client client) {
            this.client = client;
            return this;
        }

        /**
         * Method to set the step wise bid amounts.
         *
         * @param stepWiseBidAmounts the step wise bid amounts
         * @return the builder
         */
        public Builder withStepWiseBidAmounts(Double... stepWiseBidAmounts) {
            this.stepWiseBidAmounts = stepWiseBidAmounts;
            return this;
        }

        /**
         * Method to build the bid.
         *
         * @return the bid
         */
        public Bid build() {
            Bid bid = new Bid();
            bid.setClient(this.client);
            bid.setStepWiseBidAmounts(this.maxAmount);
            bid.setStepWiseLicitareMax(this.stepWiseBidAmounts);

            for (Double bidAmount : bid.getStepWiseLicitareMax()) {
                if (bidAmount > this.maxAmount) {
                    throw new IllegalArgumentException("Bid amounts cannot exceed the max amount");
                }
            }

            return bid;
        }
    }
}
