package com.auctionhouse;

import com.auctionhouse.pojo.*;
import com.auctionhouse.pojo.notifications.BrokerMessagePayload;
import com.auctionhouse.pojo.notifications.BrokerNotification;
import com.auctionhouse.pojo.notifications.Notification;
import com.auctionhouse.services.CommissionCalculatorService;
import com.auctionhouse.services.CommissionCalculatorServiceImpl;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Clasa care reprezintă casa de licitații.
 * Aceasta va conține funcționalitatile urmatoare
 * - initializarea casei de licitatii
 * - plaseaza produse
 * - efectuează licitatii
 * - notifică brokerul și clienții (cclienții vor fi anunțați prin intermediul broker)
 */
public class CasaDeLicitatii {
    private CommissionCalculatorService commissionCalculatorService = CommissionCalculatorServiceImpl.getInstance();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private List<Produs> Lista_De_Produse_Pt_Vanzare = Collections.synchronizedList(new LinkedList<>());
    private List<Client> Lista_De_Clienti_Din_Sistem = Collections.synchronizedList(new LinkedList<>());
    private List<Licitatie> Lista_Cu_Licitatiile_Active = Collections.synchronizedList(new LinkedList<>());
    private List<Broker> brokerList = Collections.synchronizedList(new LinkedList<>());
    private Map<Client, Broker> clientBrokerMap = new ConcurrentHashMap<>();

    /**
     * Constructor pentru a instanția casa de licitații.
     *
     * @param Lista_De_Produse_Pt_Vanzare
     * @param Lista_De_Clienti_Din_Sistem
     * @param Lista_Cu_Licitatiile_Active
     * @param brokerList
     */
    public CasaDeLicitatii(List<Produs> Lista_De_Produse_Pt_Vanzare, List<Client> Lista_De_Clienti_Din_Sistem, List<Licitatie> Lista_Cu_Licitatiile_Active, List<Broker> brokerList) {
        this.Lista_De_Produse_Pt_Vanzare.addAll(Lista_De_Produse_Pt_Vanzare);
        this.Lista_De_Clienti_Din_Sistem.addAll(Lista_De_Clienti_Din_Sistem);
        this.Lista_Cu_Licitatiile_Active.addAll(Lista_Cu_Licitatiile_Active);
        this.brokerList.addAll(brokerList);
    }

    /**
     * Metoda pentru a obține lista de produse de vânzare.
     *
     * @return the product list for sale.
     */
    public List<Produs> getLista_De_Produse_Pt_Vanzare() {
        return Lista_De_Produse_Pt_Vanzare;
    }

    /**
     * Metodă pentru a obține lista de clienți din sistem.
     *
     * @return the client list
     */
    public List<Client> getLista_De_Clienti_Din_Sistem() {
        return Lista_De_Clienti_Din_Sistem;
    }

    /**
     * Metodă pentru a obține lista de licitații active.
     *
     * @return the active auction list
     */
    public List<Licitatie> getActiveAuctionList() {
        return Lista_Cu_Licitatiile_Active;
    }

    /**
     * Metoda pentru a obține lista brokerilor.
     *
     * @return the broker list.
     */
    public List<Broker> getBrokerList() {
        return brokerList;
    }

    /**
     * Metoda pentru stergerea produselor vândute.
     */
    public void stergeProduseVandute() {
        List<Produs> produseVandute = Lista_De_Produse_Pt_Vanzare.stream().filter(Produs::isVandut).collect(Collectors.toList());
        this.Lista_De_Produse_Pt_Vanzare.removeAll(produseVandute);
    }

    /**
     * Metoda de a adăuga produse la casa de licitații.
     *
     * @param produseDeAdaugat the products to add
     */
    public void adaugaProduse(List<Produs> produseDeAdaugat) {
        for (Produs product : produseDeAdaugat) {
            if (!this.Lista_De_Produse_Pt_Vanzare.contains(product)) {
                this.Lista_De_Produse_Pt_Vanzare.add(product);
            }
        }
    }

    /**
     * Metoda pentru a plasa un produs
     *
     * @param licitareId
     * @param bid
     */
    public void placeBid(int licitareId, Bid bid) {
        Optional<Licitatie> licitareOptionala = Lista_Cu_Licitatiile_Active.stream().filter(a -> (a.getId() == licitareId)).findFirst();

        licitareOptionala.ifPresent(auction -> auction.placeBid(bid));

        Broker broker = clientBrokerMap.get(bid.getClient());

        if (broker == null) {
            broker = selectRandomBroker();
        }

        if (!broker.getClienti().contains(bid.getClient())) {
            broker.getClienti().add(bid.getClient());
            clientBrokerMap.putIfAbsent(bid.getClient(), broker);
        }
    }

    /**
     * Metoda de efectuare a licitației.
     *
     * @param licitareId
     */
    public void performLicitare(int licitareId) {
        Optional<Licitatie> licitareOptionala = Lista_Cu_Licitatiile_Active.stream().filter(a -> (a.getId() == licitareId)).findFirst();

        if (!licitareOptionala.isPresent()) {
            throw new IllegalArgumentException("Invalid auction id: " + licitareId);
        }

        System.out.println("Auction: " + licitareId + " started");

        // Obține licitația și produsul vândut
        Licitatie licitatie = licitareOptionala.get();
        Optional<Produs> produsOptional = Lista_De_Produse_Pt_Vanzare.stream().filter(p -> (p.getId() == licitatie.getIdProdus())).findFirst();

        if (licitatie.getLicitare().size() < licitatie.getNrParticipanti()) {
            throw new IllegalStateException("Auction: " + licitatie.getId() + " cannot start. Number of participants should be equal to the registered participants. Required: " + licitatie.getNrParticipanti() + ", Actual: " + licitatie.getLicitare().size());
        }

        // Anunța toți brokerii la începutul licitației
        for (Broker broker : brokerList) {
            broker.sendStartNotification(licitareId);
        }

        int maxSteps = licitatie.getNrPasiMaxim();
        List<Client> auctionClients = licitatie.getLicitare().stream().map(Bid::getClient).collect(Collectors.toList());
        Map.Entry<Client, Double> lastStepMaxBid = null;
        Double commission = null;

        // Pentru fiecare pas primeste suma solicitata de la client
        for (int i = 1; i <= maxSteps; i++) {
            Map<Client, Double> currentStepBids = new HashMap<>();

            for (Bid bid : licitatie.getLicitare()) {
                Client client = bid.getClient();
                // Solicita oferta de la client
                Double currentBid = bid.getCurrentBid();

                if (currentBid != null) {
                    currentStepBids.put(client, currentBid);
                }
            }

            if (currentStepBids.entrySet().isEmpty()) {

                // Dacă pașii finali nu conțin nicio ofertă, trimite o notificare ca produsul nu a fost vândut
                if (i == maxSteps) {
                    BrokerMessagePayload payload = new BrokerMessagePayload(licitareId, licitatie.getIdProdus(),
                                                                            auctionClients.stream().map(Client::getId).collect(Collectors.toList()),
                                                                            true, null, null, i, commission);

                    // Trimite o notificare către brokeri afirmand că produsul nu este vândut
                    Notification<BrokerMessagePayload> brokerNotification = new BrokerNotification(payload);
                    notifyBrokers(brokerNotification);
                }

                resetBids(licitatie);
                continue;
            }

            // Obține suma licitată maximă pentru iterație / pas curent
            Map.Entry<Client, Double> maxBidInIteration = Collections.max(currentStepBids.entrySet(), Comparator.comparing(Map.Entry::getValue));
            int maxBidClientId = maxBidInIteration.getKey().getId();
            double maxBidAmount = maxBidInIteration.getValue().doubleValue();

            if (i == maxSteps) {
                // Pasul final al licitației

                // Verifica dacă nu este respectat prețul minim al produsului.
                // Dacă nu este îndeplinită, trimite ID-ul clientului câștigat și suma licitată maximă ca nulă,
                // cu last step = true pentru a indica ca produsul nu a fost vândut
                if (!produsOptional.isPresent() || maxBidInIteration.getValue() < produsOptional.get().getPretMinim()) {
                    BrokerMessagePayload payload = new BrokerMessagePayload(licitareId, licitatie.getIdProdus(),
                                                                            auctionClients.stream().map(Client::getId).collect(Collectors.toList()),
                                                                            true, null, null, i, commission);

                    // Trimite o notificare către brokeri afirmand că produsul nu a fost vândut
                    Notification<BrokerMessagePayload> brokerNotification = new BrokerNotification(payload);
                    notifyBrokers(brokerNotification);
                    resetBids(licitatie);
                    return;
                }

                lastStepMaxBid = maxBidInIteration;

                // Verifică dacă există mai multe intrări max
                Double maxValueToCheck = maxBidInIteration.getValue();
                List<Map.Entry<Client, Double>> maxBidEntries = currentStepBids.entrySet().stream().filter(e -> (e.getValue().doubleValue() == maxValueToCheck.doubleValue())).collect(Collectors.toList());

                // Dacă există mai multe intrări maxime, clientul cu participare maximă câștigă
                if (maxBidEntries.size() > 1) {
                    lastStepMaxBid = Collections.max(maxBidEntries, Comparator.comparing(clientDoubleEntry -> clientDoubleEntry.getKey().getNrParticipari()));
                }

                maxBidClientId = lastStepMaxBid.getKey().getId();
                maxBidAmount = lastStepMaxBid.getValue();
                // Calculeaza comisionul care se duce la broker
                commission = commissionCalculatorService.calculeaza(lastStepMaxBid.getKey(), lastStepMaxBid.getValue());
            }

            // Trimite mesaje către broker cu privire la suma maximă licitată din pasul curent
            BrokerMessagePayload payload = new BrokerMessagePayload(licitareId, licitatie.getIdProdus(), auctionClients.stream().map(Client::getId).collect(Collectors.toList()), (i == maxSteps), maxBidClientId, maxBidAmount, i, commission);
            Notification<BrokerMessagePayload> brokerNotification = new BrokerNotification(payload);
            notifyBrokers(brokerNotification);
        }

        if (lastStepMaxBid != null) {
            // Actualizeaza produsul cu atributele licitației
            Double maxBidValue = lastStepMaxBid.getValue();
            produsOptional.ifPresent(p -> {
                p.setPretVanzare(maxBidValue);
                p.setVandut(true);
            });
        }

        resetBids(licitatie);
    }

    /**
     * Metoda de închidere a serviciilor.
     */
    public void shutdownServices() {
        executorService.shutdown();
    }

    /**
     * Metoda de selectare a brokerului aleatoriu.
     * Selecția brokerului se face prin generarea unui număr aleatoriu între zero și dimensiunea brokerului.
     *
     * @return the broker selected.
     */
    private Broker selectRandomBroker() {
        int min = 0;
        int max = brokerList.size();
        int random = min + new SecureRandom().nextInt(max - min);
        return brokerList.get(random);
    }

    /**
     * Metoda de notificare a brokerilor.
     *
     * @param notification
     */
    private void notifyBrokers(Notification<BrokerMessagePayload> notification) {
        for (Broker broker : brokerList) {
            executorService.execute(() -> broker.sendNotification(notification));
        }
    }

    /**
     * Metodă de resetare a ofertelor.
     *
     * @param licitatie
     */
    private void resetBids(Licitatie licitatie) {
        licitatie.getLicitare().forEach(b -> b.resetIterator());
    }
}
