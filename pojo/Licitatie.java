package com.auctionhouse.pojo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent the auction.
 */
public class Licitatie {
    private int id;
    private int nrParticipanti;
    private int idProdus;
    private int nrPasiMaxim;
    private List<Bid> bid = Collections.synchronizedList(new LinkedList<>());

    /**
     * Private Constructor
     */
    private Licitatie() {
        // Private constructor
    }

    /***
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
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the number of participants.
     *
     * @return the number of participants
     */
    public int getNrParticipanti() {
        return nrParticipanti;
    }

    /**
     * Method to set the number of participants.
     *
     * @param nrParticipanti the number of participants
     */
    private void setNrParticipanti(int nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }

    /**
     * Method to get the product id.
     *
     * @return the product id
     */
    public int getIdProdus() {
        return idProdus;
    }

    /**
     * Method to set the product id.
     *
     * @param idProdus the product id
     */
    private void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    /**
     * Method to get the number of maximum steps.
     *
     * @return the number of maximum steps
     */
    public int getNrPasiMaxim() {
        return nrPasiMaxim;
    }

    /**
     * Method to set the number of maximum steps.
     *
     * @param nrPasiMaxim the number of maximum steps
     */
    private void setNrPasiMaxim(int nrPasiMaxim) {
        this.nrPasiMaxim = nrPasiMaxim;
    }

    /**
     * Method to place a bid.
     * This will be called by the auction house once a bid is placed via it.
     *
     * @param bid the bid
     */
    public void placeBid(Bid bid) {
        this.bid.add(bid);
    }

    /**
     * Method to get the bids.
     *
     * @return the bid
     */
    public List<Bid> getLicitare() {
        return bid;
    }

    /**
     * Class to represent the auction builder.
     */
    public static class Builder {
        private int id;
        private int noParticipants;
        private int idProduct;
        private int noMaximStep;

        /**
         * Method to set the id.
         *
         * @param id the id
         * @return the builder
         */
        public Licitatie.Builder withId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Method to set the number of participants.
         *
         * @param noParticipants the number of participants
         * @return the builder
         */
        public Licitatie.Builder withNoParticipants(int noParticipants) {
            if (noParticipants < 2) {
                throw new IllegalArgumentException("Minimum number of participants to an auction should be 2");
            }

            this.noParticipants = noParticipants;
            return this;
        }

        /**
         * Method to set the product id.
         *
         * @param idProduct the product id
         * @return the builder
         */
        public Licitatie.Builder withIdProduct(int idProduct) {
            this.idProduct = idProduct;
            return this;
        }

        /**
         * Method to set the maximum number of steps.
         *
         * @param noMaximStep the maximum number of steps
         * @return the builder
         */
        public Licitatie.Builder withNoMaximStep(int noMaximStep) {
            this.noMaximStep = noMaximStep;
            return this;
        }

        /**
         * Method to build the auction.
         *
         * @return the auction
         */
        public Licitatie build() {
            Licitatie licitatie = new Licitatie();
            licitatie.setId(id);
            licitatie.setIdProdus(idProduct);
            licitatie.setNrPasiMaxim(noMaximStep);
            licitatie.setNrParticipanti(noParticipants);
            return licitatie;
        }
    }
}
