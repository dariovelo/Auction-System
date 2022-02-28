package com.auctionhouse.pojo;

/**
 * Class to represent the painting product.
 */
public class Tablou extends Produs {
    private String numePictor;
    private Enum culori;

    /**
     * Constructor to instantiate painting.
     *
     * @param id           the id
     * @param name         the name
     * @param priceMinimum the minimum price to sell the product
     * @param year         the year
     * @param numePictor  the name of the painter
     * @param culori       the colors
     */
    public Tablou(int id, String name, double priceMinimum, int year, String numePictor, Enum culori) {
        super(id, name, priceMinimum, year);
        this.numePictor = numePictor;
        this.culori = culori;
    }

    /**
     * Method to get the name of the painter.
     *
     * @return the name of the painter.
     */
    public String getNumePictor() {
        return numePictor;
    }

    /**
     * Method to set the name of the painter.
     *
     * @param numePictor the name of the painter
     */
    public void setNumePictor(String numePictor) {
        this.numePictor = numePictor;
    }

    /**
     * Method to get the colors.
     *
     * @return the colors
     */
    public Enum getCulori() {
        return culori;
    }

    /**
     * Method to set the colors.
     *
     * @param culori the colors
     */
    public void setCulori(Enum culori) {
        this.culori = culori;
    }
}
