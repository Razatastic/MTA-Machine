package MTA;

class Person {

    private double cashBalance; // Amount of money the person has
    private Card mtaCard; // The individual's personal card

    /**
     * Constructor
     *
     * @param cashInput amount of money user has.
     * @param cardInput the user's personal MTA card.
     */
    Person(int cashInput, Card cardInput) {
        this.cashBalance = cashInput;
        this.mtaCard = cardInput;
    }

    /**
     * Overloaded Constructor - to accept only cash if user doesn't have a card already
     *
     * @param cashInput amount of money user has.
     */
    Person(int cashInput) {
        this.cashBalance = cashInput;
    }

    Card getMtaCard() {
        return mtaCard;
    }

    void setMtaCard(Card mtaCard) {
        this.mtaCard = mtaCard;
    }

    double getCashBalance() {
        return cashBalance;
    }

    void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }
}
