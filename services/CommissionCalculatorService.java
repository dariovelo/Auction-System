package com.auctionhouse.services;

import com.auctionhouse.pojo.Client;

/**
 * Interface to represent the commission calculator service.
 */
public interface CommissionCalculatorService {
    /**
     * Method to calculate teh commission.
     *
     * @param clientulCastigator the winning client
     * @param maxBid        the max bid amount
     * @return the commission amount
     */
    double calculeaza(Client clientulCastigator, double maxBid);
}
