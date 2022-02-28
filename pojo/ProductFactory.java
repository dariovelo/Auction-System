package com.auctionhouse.pojo;

import com.auctionhouse.enumeration.Culori;

/**
 * Class to represent the product factory.
 */
public class ProductFactory {
    /**
     * Private constructor to stop instantiation of the product factory.
     */
    private ProductFactory() {
        // Private constructor
    }

    /**
     * Method to get create the furniture product.
     *
     * @param id           the id
     * @param name         the name
     * @param priceMinimum the minimum price to sell the product
     * @param year         the year
     * @param type         the type
     * @param material     the material
     * @return the created furniture product
     */
    public static Mobila create(int id, String name, double priceMinimum, int year, String type, String material) {
        return new Mobila(id, name, priceMinimum, year, type, material);
    }

    /**
     * Method to create the jewelry product.
     *
     * @param id            the id
     * @param name          the name
     * @param priceMinimum  the minimum price to sell the product
     * @param year          the year
     * @param material      the material
     * @param stonePrecious the stone precious
     * @return the created jewelry product
     */
    public static Bijuterie create(int id, String name, double priceMinimum, int year, String material, boolean stonePrecious) {
        return new Bijuterie(id, name, priceMinimum, year, material, stonePrecious);
    }

    /**
     * Method to create painting product.
     *
     * @param id           the id
     * @param name         the name
     * @param priceMinimum the minimum price to sell the product
     * @param year         the year
     * @param namePainter  the name of the painter
     * @param colors       the colors
     * @return the created painting product
     */
    public static Tablou create(int id, String name, double priceMinimum, int year, String namePainter, Culori colors) {
        return new Tablou(id, name, priceMinimum, year, namePainter, colors);
    }
}
