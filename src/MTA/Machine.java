package MTA;

import java.util.Scanner;

class Machine {
    private Person currentUser;
    private Card currentCard;

    Machine(Person user) {
        this.currentUser = user;
        this.currentCard = user.getMtaCard();
    }

    /**
     * Greet current user and take in their input
     *
     * @return userInput - What the user wants to do at the machine.
     */
    void getUserInput() {
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
            System.out.println("What would you like to do today?");
            while (!in.hasNextInt()) {
                System.out.println("Please enter a number from 1 to " + options.length + ".");
                in.next();
            }
            userInput = in.nextInt();
            in.close();
        } while (userInput < 1 || userInput > options.length);
        processInput(userInput);
    }

    private void processInput(int userInput) {
        if (userInput == 1) { // Charge user $1 for a new card and update their Person object accordingly
            if (currentUser.getCashBalance() - 1 >= 0) {
                currentUser.setCashBalance(currentUser.getCashBalance() - 1);
                currentUser.setMtaCard(new Card());
            } else { // If they don't have enough money, then throw an error and exit
                System.out.println("Sorry. It seems as if you do not have enough money. Goodbye!");
            }
        } else if (userInput == 2) { // Show card's balance, exp. date + whether/not it has sufficient fare
            System.out.println("Your card has $" + currentCard.getCardBalance() + " left.");
            System.out.println("This is enough for " + currentCard.ridesLeft() + " rides.");
            System.out.println("Expiration Date: " + currentCard.getExpDate());
        } else { // Add money to card
            Scanner in = new Scanner(System.in);
            System.out.println("How much money would you like to add?");
            double reloadAmount = in.nextDouble();
            currentCard.setCardBalance(currentCard.getCardBalance() + reloadAmount);
        }
    }

    /**
     * Updates currentUser and currentCard values with the new user's info
     *
     * @param newPerson The next machine user
     */
    void newUser(Person newPerson) {
        this.currentUser = newPerson;
        this.currentCard = newPerson.getMtaCard();
    }

}
