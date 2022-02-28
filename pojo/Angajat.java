package com.auctionhouse.pojo;

/**
 * Clasa care să reprezinte angajatul.
 */
abstract class Angajat {
    protected int id;
    protected String nume;

    /**
     * Constructor pentru instanțierea unui angajat.
     *
     * @param id   the id
     * @param nume the name
     */
    public Angajat(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    /**
     * Method to get the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Method to get the name.
     *
     * @return the name
     */
    public String getNume() {
        return nume;
    }
}
