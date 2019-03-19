package MTA;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Machine {
    // Both cardDatabase and currentCardIdx are static so that they can be shared between multiple machine objects
    private static ArrayList<Card> cardDatabase = new ArrayList<>();
    private static int currentCardIdx = 0;
    // currentUser and currentCard are simply used for easier tracking of both the user and their card
    private static Person currentUser;
    private static Card currentCard;

    /**
     * Adds an empty Card object to the database
     */
    static void addCard() {
        cardDatabase.add(new Card(currentCardIdx));
        currentCardIdx++;
    }

    /**
     * Adds a Card object with a specified value to the database
     *
     * @param balance - The card's initial balance
     */
    static void addCard(double balance) {
        cardDatabase.add(new Card(currentCardIdx, balance));
        currentCardIdx++;
    }

    /**
     * Starts the program by updating the currentUser and greeting them with the main menu
     *
     * @param name - The current user object
     */
    static void start(Person name) {
        setCurrentUser(name);
        mainMenu();
    }

    /**
     * Prints user's cash balance and list of all their metrocards
     */
    private static void printHeader() {
        System.out.println("-------------------- MTA Machine --------------------");
        System.out.println("Current Balance: $" + String.format("%.2f", currentUser.getCashBalance()));
        System.out.print("Metro Card(s): ");
        for (int i = 0; i < currentUser.getMtaCards().size(); i++) {
            System.out.print("[" + currentUser.getMtaCard(i) + "] ");
        }
        System.out.println();
    }

    /**
     * Greet current user and take in their input
     */
    private static void mainMenu() {
        String[] options = new String[4]; // An array of possible options
        options[0] = ("Get a New Card"); // Charges user $1 for new card
        options[1] = ("Check Card Status"); // Shows balance, expiration date, whether it has sufficient fare
        options[2] = ("Add Balance"); // Add money to card
        options[3] = ("Exit"); // Leave machine

        Scanner in = new Scanner(System.in);
        printHeader();
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
        } while (userInput < 1 || userInput > options.length);
        processInput(userInput);
    }

    /**
     * Processes user input from mainMenu()
     *
     * @param userInput - Return value from mainMenu()
     */
    private static void processInput(int userInput) {
        if (userInput == 1) { // Charge user $1 for a new card and update their Person object accordingly
            dispenseCard();
        } else if (userInput == 2) { // Show card's balance, exp. date + whether/not it has sufficient fare
            if (currentUser.getMtaCards().size() > 0) {
                cardStatus();
            } else {
                noCardWarning();
            }
        } else if (userInput == 3) { // Add money to card
            if (currentUser.getMtaCards().size() > 0) {
                addMoney();
                mainMenu();
            } else {
                noCardWarning();
            }
        } else if (userInput == 4) { // End current user's turn
            assert true;
        } else { // Re-prompt user for valid input
            System.out.println("Please enter a valid input.");
            mainMenu();
        }
    }

    /**
     * Update the currentUser and currentCard object
     *
     * @param user - The Person object you want to switch to
     */
    private static void setCurrentUser(@NotNull Person user) {
        currentUser = user;

        if (currentUser.getMtaCards().size() > 0) {
            currentCard = cardDatabase.get(user.getMtaCard(0));
        } else {
            currentCard = null;
        }
    }

    /**
     * Depending on the current user's cash balance, adds a new card to their mtaCards list if they have enough.
     * Otherwise, re-prompts the user for a valid amount.
     */
    private static void dispenseCard() {
        int moneyAdded = addMoney();
        currentUser.setCashBalance(currentUser.getCashBalance() - 1); // Charge $1 for the new metrocard fee
        currentUser.setMtaCard(cardDatabase.size() + 1);
        currentCard = new Card(cardDatabase.size() + 1, moneyAdded); // Update currentCard reference
        cardDatabase.add(currentCard); // Add the new card to the database
        System.out.println("Your new card balance is $" + String.format("%.2f", currentCard.getCardBalance()));
        mainMenu();
    }

    private static int addMoney() {
        int moneyAdded = 0;

        Scanner in = new Scanner(System.in);
        System.out.println("How much money would you like to add?");
        System.out.println("Current Balance: $" + String.format("%.2f", currentUser.getCashBalance()));

        // Edge cases
        try {
            moneyAdded = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a whole number.");
            addMoney();
        }

        if (moneyAdded <= 0) {
            System.out.println("Please enter a valid amount.");
            addMoney();
        }

        if (currentUser.getCashBalance() >= moneyAdded + 1) { // First check if user has enough money
            currentUser.setCashBalance(currentUser.getCashBalance() - moneyAdded); // Charge user moneyAdded amount
            // Print updated balance
            if (currentCard != null) {
                System.out.println("Your new card balance is $" + String.format("%.2f", currentCard.getCardBalance()));
            }
        } else {
            System.out.println("Seems as if you don't have enough funds! Please try a lower amount.");
            addMoney();
        }
        return moneyAdded;
    }

    /**
     * Calculates number of rides left on the card given the cardBalance.
     *
     * @return number of rides left
     */
    private static int ridesLeft() {
        return (int) Math.floor(currentCard.getCardBalance() / 2.75);
    }

    /**
     * Prints out the current card's balance and amount of rides left
     */
    private static void cardStatus() {
        System.out.println("Your card has $" + String.format("%.2f", currentCard.getCardBalance()) + " left.");
        System.out.println("This is enough for " + ridesLeft() + " rides.");
        mainMenu();
    }

    /**
     * Prints out a warning telling the user they must first purchase a card if they don't have one
     */
    private static void noCardWarning() {
        System.out.println("********** You need to purchase a card first! **********");
        mainMenu();
    }
}