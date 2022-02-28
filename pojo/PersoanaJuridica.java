package com.auctionhouse.pojo;

import com.auctionhouse.enumeration.Companie;

/**
 * Class to represent the legal entity client.
 */
public class PersoanaJuridica extends Client {
    private Companie companie;
    private double capitalSocial;

    /**
     * Constructor to instantiate the legal entity.
     *
     * @param id            the id
     * @param name          the name
     * @param address       the address
     * @param companie       the company
     * @param capitalSocial the social capital
     */
    public PersoanaJuridica(int id, String name, String address, Companie companie, double capitalSocial) {
        super(id, name, address);
        this.companie = companie;
        this.capitalSocial = capitalSocial;
    }

    /**
     * Method to get the company.
     *
     * @return the company
     */
    public Companie getCompany() {
        return companie;
    }

    /**
     * Method to set the company.
     *
     * @param companie the company
     */
    public void setCompany(Companie companie) {
        this.companie = companie;
    }

    /**
     * Method to get the social capital.
     *
     * @return the social capital
     */
    public double getCapitalSocial() {
        return capitalSocial;
    }

    /**
     * Method to set the social capital.
     *
     * @param capitalSocial the social capital
     */
    public void setCapitalSocial(double capitalSocial) {
        this.capitalSocial = capitalSocial;
    }
}
