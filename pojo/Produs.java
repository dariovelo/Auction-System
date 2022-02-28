package com.auctionhouse.pojo;

/**
 * Class to represent the product.
 */
public class Produs {
    private int id;
    private String nume;
    private double pretVanzare;
    private double pretMinim;
    private int an;
    private boolean vandut;

    /**
     * Constructor to instantiate product.
     *
     * @param id           the id
     * @param nume         the name
     * @param pretMinim the minimum price to sell the product
     * @param an         the year
     */
    public Produs(int id, String nume, double pretMinim, int an) {
        this.id = id;
        this.nume = nume;
        this.pretMinim = pretMinim;
        this.an = an;
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
     * Method to get the price sale.
     * This is the price where the product has been sold at the auction.
     *
     * @return the price where the product has been sold at the auction.
     */
    public double getPretVanzare() {
        return pretVanzare;
    }

    /**
     * Method to set the price sale.
     *
     * @param pretVanzare the price sale
     */
    public void setPretVanzare(double pretVanzare) {
        this.pretVanzare = pretVanzare;
    }

    /**
     * Method to get the price minimum.
     * This is the minimum price of the product to be sold.
     *
     * @return the minimum price of the product to be sold.
     */
    public double getPretMinim() {
        return pretMinim;
    }

    /**
     * Method to set the price minimum.
     *
     * @param pretMinim the minimum price of the product to be sold
     */
    public void setPretMinim(double pretMinim) {
        this.pretMinim = pretMinim;
    }

    /**
     * Method to get the year.
     *
     * @return the year
     */
    public int getAn() {
        return an;
    }

    /**
     * Method to set the year.
     *
     * @param an the year
     */
    public void setAn(int an) {
        this.an = an;
    }

    /**
     * Method to check whether the product is sold.
     *
     * @return true if sold and false if not
     */
    public boolean isVandut() {
        return vandut;
    }

    /**
     * Method to set the sold attribute.
     *
     * @param vandut true if sold and false if not
     */
    public void setVandut(boolean vandut) {
        this.vandut = vandut;
    }
}
