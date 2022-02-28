package com.auctionhouse.pojo;

import com.auctionhouse.enumeration.Companie;

/**
 * Class to represent the client factory.
 */
public class ClientFactory {
    /**
     * Private constructor to stop initialization of the client factory.
     */
    private ClientFactory() {
        // Private constructor
    }

    /**
     * Factory method to create an individual client.
     *
     * @param id        the id
     * @param name      the name
     * @param address   the address
     * @param birthDate the birth date
     * @return an individual client
     */
    public static PersoanaFizica create(int id, String name, String address, String birthDate) {
        return new PersoanaFizica(id, name, address, birthDate);
    }

    /**
     * Factory method to create a legal entity client.
     *
     * @param id            the id
     * @param nume          the name
     * @param adresa       the address
     * @param companie       the company
     * @param capitalSocial the social capital
     * @return a legal entity client
     */
    public static PersoanaJuridica create(int id, String nume, String adresa, Companie companie, double capitalSocial) {
        return new PersoanaJuridica(id, nume, adresa, companie, capitalSocial);
    }
}
