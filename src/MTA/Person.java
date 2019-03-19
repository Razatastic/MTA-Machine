package MTA;

import java.util.ArrayList;

class Person {

    private double cashBalance; // Amount of money the person has
    private ArrayList<Integer> mtaCards = new ArrayList<>(); // The card's serial number - used for lookup in machine's database

    /**
     * Constructor - Creates a Person object with a specified cash balance
     *
     * @param cashInput amount of money user has.
     */
    Person(int cashInput) {
        this.cashBalance = cashInput;
    }

    /**
     * Overloaded Constructor - Creates a Person object with a specified cash balance and a metrocard serial number
     * which corresponds to the card database found in Machine.java
     *
     * @param cashInput amount of money user has.
     */
    Person(int cashInput, int cardSerialNum) {
        this.cashBalance = cashInput;
        this.mtaCards.add(cardSerialNum);
    }

    /**
     * @return mtaCards - List of all the user's metrocards
     */
    ArrayList<Integer> getMtaCards() {
        return mtaCards;
    }

    /**
     * @param serialNum - The serial number you're looking for in the mtaCards list
     * @return the value at the specific serialNum
     */
    int getMtaCard(int serialNum) {
        return mtaCards.get(serialNum);
    }

    void setMtaCard(int serialNum) {
        this.mtaCards.add(serialNum);
    }

    double getCashBalance() {
        return cashBalance;
    }

    void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
}
