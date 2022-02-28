package com.auctionhouse.pojo;

import com.auctionhouse.CasaDeLicitatii;

import java.util.List;

/**
 * Clasa pentru a reprezenta administratorul.
 */
public class Administrator extends Angajat {
    /**
     * Constructor pentru instanțierea administratorului.
     *
     * @param id   the id
     * @param nume the nume
     */
    public Administrator(int id, String nume) {
        super(id, nume);
    }

    /**
     * Metoda de a adăuga produse noi la casa de licitații.
     *
     * @param casaDeLicitatii the auction house.
     * @param newProducts  the new products list to add
     */
    public void adaugaProduseNoiLaCasaDeLicitații(CasaDeLicitatii casaDeLicitatii, List<Produs> newProducts) {
        casaDeLicitatii.adaugaProduse(newProducts);
    }
}
