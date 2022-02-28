package com.auctionhouse.services;

import com.auctionhouse.pojo.Client;
import com.auctionhouse.pojo.PersoanaFizica;
import com.auctionhouse.pojo.PersoanaJuridica;

import java.util.HashMap;
import java.util.Map;

/**
 * Clasa pentru a reprezenta implementarea serviciului de calcul al comisioanelor.
 */
public class CommissionCalculatorServiceImpl implements CommissionCalculatorService {
    private static volatile CommissionCalculatorServiceImpl commissionCalculatorService;

    private static final String CATEGORY_C1 = "C1";
    private static final String CATEGORY_C2 = "C2";
    private static final String CATEGORY_C3 = "C3";
    private static final String CATEGORY_C4 = "C4";
    private static final Map<String, Double> CATEGORY_PERCENTAGE_MAP = new HashMap<>();

    static {
        CATEGORY_PERCENTAGE_MAP.put(CATEGORY_C1, 0.20);
        CATEGORY_PERCENTAGE_MAP.put(CATEGORY_C2, 0.15);
        CATEGORY_PERCENTAGE_MAP.put(CATEGORY_C3, 0.25);
        CATEGORY_PERCENTAGE_MAP.put(CATEGORY_C4, 0.10);
    }

    /**
     * Constructor privat
     */
    private CommissionCalculatorServiceImpl() {
        // Constructor made private
    }

    /**
     * Method to get the singleton instance of the service.
     *
     * @return the singleton instance of the service
     */
    public static CommissionCalculatorServiceImpl getInstance() {
        if (commissionCalculatorService == null) {
            synchronized (CommissionCalculatorServiceImpl.class) {
                if (commissionCalculatorService == null) {
                    commissionCalculatorService = new CommissionCalculatorServiceImpl();
                }
            }
        }

        return commissionCalculatorService;
    }

    /**
     * Metoda de calcul a comisionului.
     *
     * @param clientulCastigator the winning client
     * @param maxBid        the max bid amount
     * @return the commission amount
     */
    @Override
    public double calculeaza(Client clientulCastigator, double maxBid) {
        Double commissionPercentage = CATEGORY_PERCENTAGE_MAP.get(getCommissionCategory(clientulCastigator));

        if (commissionPercentage == null) {
            return 0;
        }

        return maxBid * commissionPercentage;
    }

    /**
     * Metoda de a obține categoria de comision pentru un client câștigător.
     *
     * @param clientulCastigator the winning client
     * @return the commission category
     */
    private String getCommissionCategory(Client clientulCastigator) {
        if (clientulCastigator instanceof PersoanaFizica) {
            if (clientulCastigator.getNrParticipari() < 5) {
                return CATEGORY_C1;
            } else {
                return CATEGORY_C2;
            }
        } else if (clientulCastigator instanceof PersoanaJuridica) {
            if (clientulCastigator.getNrParticipari() < 25) {
                return CATEGORY_C3;
            } else {
                return CATEGORY_C4;
            }
        }

        return null;
    }
}
