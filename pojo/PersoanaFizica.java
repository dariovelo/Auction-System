package com.auctionhouse.pojo;

/**
 * Class to represent an individual client.
 */
public class PersoanaFizica extends Client {
    private String dataNastere;

    /**
     * Constructor to instantiate the individual.
     *
     * @param id        the id
     * @param name      the name
     * @param address   the address
     * @param dataNastere the birth date
     */
    public PersoanaFizica(int id, String name, String address, String dataNastere) {
        super(id, name, address);
        this.dataNastere = dataNastere;
    }

    /**
     * Method to get the birth date.
     *
     * @return the birth date
     */
    public String getDataNastere() {
        return dataNastere;
    }

    /**
     * Method to set the birth date.
     *
     * @param dataNastere the birth date
     */
    public void setDataNastere(String dataNastere) {
        this.dataNastere = dataNastere;
    }
}
