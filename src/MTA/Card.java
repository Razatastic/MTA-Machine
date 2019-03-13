package MTA;

import java.util.Date;

class Card {
    private long serialNum;
    private double cardBalance;
    private Date expDate;

    Card() {
        this.serialNum = (long) (Math.random() * 1000000L);
        this.cardBalance = 0;
        this.expDate = new Date();
    }

    /**
     * Calculates number of rides left on the card given the cardBalance.
     *
     * @return number of rides left
     */
    int ridesLeft() {
        return (int) Math.floor(this.cardBalance / 2.75);
    }

    double getCardBalance() {
        return cardBalance;
    }

    void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
