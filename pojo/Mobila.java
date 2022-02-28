package com.auctionhouse.pojo;

/**
 * Class to represent the furniture product.
 */
public class Mobila extends Produs {
    private String tip;
    private String material;

    /**
     * Constructor to instantiate furniture.
     *
     * @param id           the id
     * @param name         the name
     * @param priceMinimum the price minimum to be sold
     * @param year         the year
     * @param tip         the type
     * @param material     the material
     */
    public Mobila(int id, String name, double priceMinimum, int year, String tip, String material) {
        super(id, name, priceMinimum, year);
        this.tip = tip;
        this.material = material;
    }

    /**
     * Method to get the type.
     *
     * @return the type
     */
    public String getTip() {
        return tip;
    }

    /**
     * Method to set the type.
     *
     * @param tip the type
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * Method to get the material.
     *
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Method to set the material.
     *
     * @param material the material
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}
