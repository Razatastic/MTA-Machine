package MTA;

//import java.util.Date;

class Card {
    private int serialNumber;
    private double cardBalance;
//    private Date expDate;

    Card(int serialNum) {
        this.serialNumber = serialNum;
        this.cardBalance = 0;
//        this.expDate = new Date();
    }

    Card(int serialNum, double moneyAdded) {
        this.serialNumber = serialNum;
        this.cardBalance = moneyAdded;
//        this.expDate = new Date();
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

//    Date getExpDate() {
//        return expDate;
//    }
//
//    void setExpDate(Date expDate) {
//        this.expDate = expDate;
//    }
}
