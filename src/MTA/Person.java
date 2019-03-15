package MTA;

class Person {

    private double cashBalance; // Amount of money the person has
    private int mtaCard; // The card's serial number - used for lookup in machine's database

    /**
     * Constructor
     *
     * @param cashInput amount of money user has.
     */
    Person(int cashInput, int cardSerialNum) {
        this.cashBalance = cashInput;
        this.mtaCard = cardSerialNum;
    }

    Person(int cashInput) {
        this.cashBalance = cashInput;
        this.mtaCard = -1;
    }

    int getMtaCard() {
        return mtaCard;
    }

    void setMtaCard(int mtaCard) {
        this.mtaCard = mtaCard;
    }

    double getCashBalance() {
        return cashBalance;
    }

    void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
}
