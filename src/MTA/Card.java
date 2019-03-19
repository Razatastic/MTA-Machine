package MTA;


class Card {
    private int serialNumber;
    private double cardBalance;

    Card(int serialNum) {
        this.serialNumber = serialNum;
        this.cardBalance = 0;
    }

    Card(int serialNum, double moneyAdded) {
        this.serialNumber = serialNum;
        this.cardBalance = moneyAdded;
    }

    int getSerialNumber() {
        return serialNumber;
    }

    double getCardBalance() {
        return cardBalance;
    }

    void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }
}
