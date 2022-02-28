package com.auctionhouse.tests;

import com.auctionhouse.CasaDeLicitatii;
import com.auctionhouse.enumeration.Culori;
import com.auctionhouse.enumeration.Companie;
import com.auctionhouse.pojo.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to represent Test 6.
 * Test : Commission is calculated for legal entities with less than 25 participation count. (Category C3 commission)
 *        All products are sold
 *        Broker cleans sold products
 *        Administrator adds new products
 */
public class Test6 {
    /**
     * Method to run the test.
     */
    public static void run() {
        System.out.println("\n----- Running Test 6 -----\n");
        Administrator administrator = new Administrator(100, "admin");

        Client client1 = ClientFactory.create(1, "client1", "addr1", "1991-01-01");
        Client client2 = ClientFactory.create(2, "client2", "addr2", Companie.SA, 20.0);
        Client client3 = ClientFactory.create(3, "client3", "addr3", "1993-01-01");
        Client client4 = ClientFactory.create(4, "client4", "addr4", Companie.SRL, 50.0);
        Client client5 = ClientFactory.create(5, "client5", "addr5", "1995-01-01");
        Client client6 = ClientFactory.create(6, "client6", "addr6", "1996-01-01");

        Broker broker1 = new Broker(1, "broker1");
        Broker broker2 = new Broker(2, "broker2");

        Produs product1 = ProductFactory.create(1, "furniture1", 20.0, 2021, "modern-type", "wood");
        Produs product2 = ProductFactory.create(2, "jewelry2", 100.0, 2021, "gold", true);
        Produs product3 = ProductFactory.create(3, "painting3", 20.00, 2020, "painter1", Culori.ACRILIC);
        Produs product4 = ProductFactory.create(4, "furniture4", 49.0, 2019, "modern-type", "wood");
        Produs product5 = ProductFactory.create(5, "jewelry5", 110.0, 2021, "silver", false);
        Produs product6 = ProductFactory.create(6, "painting6", 150.00, 2020, "painter2", Culori.ULEI);
        Produs product7 = ProductFactory.create(7, "furniture7", 49.0, 2019, "modern-type", "wood");
        Produs product8 = ProductFactory.create(8, "jewelry8", 105.00, 2000, "gold", true);
        Produs product9 = ProductFactory.create(9, "painting9", 52.00, 2020, "painter1", Culori.TEMPERA);
        Produs product10 = ProductFactory.create(10, "furniture10", 500.0, 2021, "modern-type", "wood");
        Produs product11 = ProductFactory.create(11, "jewelry11", 1000.0, 2021, "gold", false);
        Produs product12 = ProductFactory.create(12, "painting12", 20.00, 2020, "painter1", Culori.ACRILIC);
        Produs product13 = ProductFactory.create(13, "furniture13", 120.0, 2019, "modern-type", "wood");
        Produs product14 = ProductFactory.create(14, "jewelry14", 700.0, 2021, "silver", false);
        Produs product15 = ProductFactory.create(15, "painting15", 180.00, 2020, "painter2", Culori.ULEI);
        Produs product16 = ProductFactory.create(16, "furniture16", 59.0, 2019, "modern-type", "wood");
        Produs product17 = ProductFactory.create(17, "jewelry17", 112.00, 2000, "gold", true);
        Produs product18 = ProductFactory.create(18, "painting18", 90.00, 2020, "painter1", Culori.TEMPERA);
        Produs product19 = ProductFactory.create(19, "furniture19", 220.0, 2021, "modern-type", "wood");
        Produs product20 = ProductFactory.create(20, "jewelry20", 300.0, 2021, "gold", true);

        Licitatie licitatie1 = new Licitatie.Builder().withId(1).withNoParticipants(5).withNoMaximStep(3).withIdProduct(1).build();
        Licitatie licitatie2 = new Licitatie.Builder().withId(2).withNoParticipants(3).withNoMaximStep(4).withIdProduct(4).build();
        Licitatie licitatie3 = new Licitatie.Builder().withId(3).withNoParticipants(2).withNoMaximStep(4).withIdProduct(3).build();
        Licitatie licitatie4 = new Licitatie.Builder().withId(4).withNoParticipants(3).withNoMaximStep(4).withIdProduct(4).build();

        Bid bid11 = new Bid.Builder().withClient(client1).withMaxAmount(20.0).withStepWiseBidAmounts(5.0, 11.9, 10.0, 19.00).build();
        Bid bid21 = new Bid.Builder().withClient(client2).withMaxAmount(50.0).withStepWiseBidAmounts(4.0, 6.0, 20.9, 49.0).build();
        Bid bid31 = new Bid.Builder().withClient(client3).withMaxAmount(18.0).withStepWiseBidAmounts(3.0, 4.9, 11.0, 18.0).build();
        Bid bid41 = new Bid.Builder().withClient(client4).withMaxAmount(58.0).withStepWiseBidAmounts(3.0, 11.9, 50.0, 58.0).build();
        Bid bid51 = new Bid.Builder().withClient(client5).withMaxAmount(41.0).withStepWiseBidAmounts(3.0, 21.9, 22.0, 41.0).build();
        Bid bid61 = new Bid.Builder().withClient(client6).withMaxAmount(50.0).withStepWiseBidAmounts(3.0, 11.9, 30.0, 50.0).build();
        Bid bid12 = new Bid.Builder().withClient(client1).withMaxAmount(60.0).withStepWiseBidAmounts(10.0, 20.0, 30.0, 60.0).build();
        Bid bid22 = new Bid.Builder().withClient(client2).withMaxAmount(100.0).withStepWiseBidAmounts(33.0, 41.9, 50.0, 100.0).build();
        Bid bid32 = new Bid.Builder().withClient(client3).withMaxAmount(30.0).withStepWiseBidAmounts(3.0, 11.9, 25.0, 28.0).build();
        Bid bid42 = new Bid.Builder().withClient(client4).withMaxAmount(40.0).withStepWiseBidAmounts(3.0, 12.9, 29.0, 40.0).build();
        Bid bid52 = new Bid.Builder().withClient(client5).withMaxAmount(20.0).withStepWiseBidAmounts(3.0, 11.9, 19.0, 19.5).build();
        Bid bid62 = new Bid.Builder().withClient(client6).withMaxAmount(22.0).withStepWiseBidAmounts(3.0, 11.9, 20.0, 22.0).build();

        List<Client> clientList = new LinkedList<>();
        clientList.add(client1);
        clientList.add(client2);
        clientList.add(client3);
        clientList.add(client4);
        clientList.add(client5);
        clientList.add(client6);

        List<Broker> brokerList = new LinkedList<>();
        brokerList.add(broker1);
        brokerList.add(broker2);

        List<Produs> productList = new LinkedList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);
        productList.add(product9);
        productList.add(product10);
        productList.add(product11);
        productList.add(product12);
        productList.add(product13);
        productList.add(product14);
        productList.add(product15);
        productList.add(product16);
        productList.add(product17);
        productList.add(product18);
        productList.add(product19);
        productList.add(product20);

        List<Licitatie> licitatieList = new LinkedList<>();
        licitatieList.add(licitatie1);
        licitatieList.add(licitatie2);
        licitatieList.add(licitatie3);
        licitatieList.add(licitatie4);

        // Create auction house
        CasaDeLicitatii casaDeLicitatii = new CasaDeLicitatii(productList, clientList, licitatieList, brokerList);

        casaDeLicitatii.placeBid(1, bid11);
        casaDeLicitatii.placeBid(1, bid21);
        casaDeLicitatii.placeBid(1, bid31);
        casaDeLicitatii.placeBid(1, bid41);
        casaDeLicitatii.placeBid(1, bid51);

        casaDeLicitatii.placeBid(2, bid61);
        casaDeLicitatii.placeBid(2, bid12);
        casaDeLicitatii.placeBid(2, bid22);

        casaDeLicitatii.placeBid(3, bid32);
        casaDeLicitatii.placeBid(3, bid41);

        casaDeLicitatii.placeBid(4, bid52);
        casaDeLicitatii.placeBid(4, bid62);
        casaDeLicitatii.placeBid(4, bid21);

        try {
            casaDeLicitatii.performLicitare(1);
            waitTillLogsPrinted();
            casaDeLicitatii.performLicitare(2);
            waitTillLogsPrinted();
            casaDeLicitatii.performLicitare(3);
            waitTillLogsPrinted();
            casaDeLicitatii.performLicitare(4);
        } finally {
            casaDeLicitatii.shutdownServices();
        }

        broker1.cleanSoldProductsInAuctionHouse(casaDeLicitatii);
        System.out.println("Broker cleaned sold products. Product list after cleaning : " + casaDeLicitatii.getLista_De_Produse_Pt_Vanzare().stream().map(Produs::getId).collect(Collectors.toList()));
        administrator.adaugaProduseNoiLaCasaDeLicitații(casaDeLicitatii, Arrays.asList(product1, product2));
        System.out.println("Administrator added new products. Product list : " + casaDeLicitatii.getLista_De_Produse_Pt_Vanzare().stream().map(Produs::getId).collect(Collectors.toList()) + "\n\n");
    }

    /**
     * THIS IS ONLY DONE FOR READABILITY OF LOGS
     * -----------------------------------------
     * Method to thread sleep until the logs are printed.
     * NOTE: When running in parallel, the system will behave in the expected way and the logs will be printed  in parallel due to multi threading.
     *       In order to help the viewer of the logs to read it according to the order, this method is used.
     *       If required please remove the method and you will be able to see the system runs in parallel as expected.
     */
    private static void waitTillLogsPrinted() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
