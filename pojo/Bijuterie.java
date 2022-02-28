package com.auctionhouse.pojo;

/**
 * Clasa pentru a reprezenta produsul de bijuterii.
 */
public class Bijuterie extends Produs {
    private String material;
    private boolean piatraPretioasa;

    /**
     * Constructor pentru instanțierea bijuteriilor.
     *
     * @param id             the id
     * @param nume           the name
     * @param pretMinim      the price minimum to be sold
     * @param an            the year
     * @param material      the material
     * @param piatraPretioasa the stone precious
     */
    public Bijuterie(int id, String nume, double pretMinim, int an, String material, boolean piatraPretioasa) {
        super(id, nume, pretMinim, an);
        this.material = material;
        this.piatraPretioasa = piatraPretioasa;
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

    /**
     * Method to check whether stone precious.
     *
     * @return true if stone precious, else false
     */
    public boolean isPiatraPretioasa() {
        return piatraPretioasa;
    }

    /**
     * Method to set the stone precious.
     *
     * @param piatraPretioasa true if stone precious and false if not
     */
    public void setPiatraPretioasa(boolean piatraPretioasa) {
        this.piatraPretioasa = piatraPretioasa;
    }
}
