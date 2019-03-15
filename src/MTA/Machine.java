package MTA;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

class Machine {
    // Both cardDatabase and currentCardIdx are static so that they can be shared between multiple machine objects
    private static ArrayList<Card> cardDatabase = new ArrayList<>();
    private static int currentCardIdx = 0;
    // currentUser and currentCard are simply used for easier tracking of both the user and their card
    private Person currentUser;
    private Card currentCard;

    static void addCard() {
        cardDatabase.add(new Card(currentCardIdx));
        currentCardIdx++;
    }

    static void addCard(double balance) {
        cardDatabase.add(new Card(currentCardIdx, balance));
        currentCardIdx++;
    }

    private void setCurrentUser(@NotNull Person user) {
        this.currentUser = user;
        this.currentCard = cardDatabase.get(user.getMtaCard());
    }


    private void dispenseCard() {
        Scanner in = new Scanner(System.in);
        System.out.println("How much money would you like to add?");
        int moneyAdded = in.nextInt();
        if (currentUser.getCashBalance() >= moneyAdded + 1) {
            in.close();
            // Charge $1 for the new metrocard fee
            currentUser.setCashBalance(currentUser.getCashBalance() - 1);
            currentUser.setMtaCard(cardDatabase.size() + 1);
            // Charge however much the user wants to load onto the card
            currentUser.setCashBalance(currentUser.getCashBalance() - moneyAdded);
            cardDatabase.add(new Card(cardDatabase.size() + 1, moneyAdded));
        } else {
            System.out.println("Seems as if you don't have enough funds! Please try a lower amount.");
            dispenseCard();
        }
    }

    /**
     * Greet current user and take in their input
     */
    void getUserInput(Person name) {
        setCurrentUser(name);
        String[] options = new String[3]; // An array of possible options
        options[0] = ("Get a New Card"); // Charges user $1 for new card
        options[1] = ("Check Card Status"); // Shows balance, expiration date, whether it has sufficient fare
        options[2] = ("Add Balance"); // Add money to card

        Scanner in = new Scanner(System.in);
        System.out.println("What would you like to do today?");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        int userInput;
        do {
            while (!in.hasNextInt()) {
                System.out.println("Please enter a number from 1 to " + options.length + ".");
                in.next();
            }
            userInput = in.nextInt();
            in.close();
        } while (userInput < 1 || userInput > options.length);
        processInput(userInput);
    }

    /**
     * Calculates number of rides left on the card given the cardBalance.
     *
     * @return number of rides left
     */
    private int ridesLeft() {
        return (int) Math.floor(currentCard.getCardBalance() / 2.75);
    }

    private void cardStatus() {
        System.out.println("Your card has $" + currentCard.getCardBalance() + " left.");
//        System.out.println("Expiration Date: " + currentCard.getExpDate());
        System.out.println("This is enough for " + ridesLeft() + " rides.");
    }

    private void processInput(int userInput) {
        if (userInput == 1) { // Charge user $1 for a new card and update their Person object accordingly
            dispenseCard();
        } else if (userInput == 2) { // Show card's balance, exp. date + whether/not it has sufficient fare
            if (currentCard.getSerialNumber() != -1) {
                cardStatus();
            } else {
                System.out.println("You need to purchase a card first!");
            }
        } else { // Add money to card
            Scanner in = new Scanner(System.in);
            System.out.println("How much money would you like to add?");
            double reloadAmount = in.nextDouble();
            currentCard.setCardBalance(currentCard.getCardBalance() + reloadAmount);
        }
    }
}
