package MTA;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // TEST CASE 1 - John has $50 and doesn't own a metro card. He wants to purchase one and fill it with $10.
        Person John = new Person(50);
        // TEST CASE 2 - Mary already has a metro card and simply wants to check its balance. She has no money.
        Person Mary = new Person(140, 1);

        // Create a line of people waiting for the machine
        Queue<Person> lineOfPeople = new LinkedList<>();
        lineOfPeople.add(John);
        lineOfPeople.add(Mary);

        Machine.addCard(); // Add a card with no money to the database - Index 0
        Machine.addCard(41.32); // Add a card with $4.32 to the database - Index 1
        Machine.addCard(10); // Add a card with $10 to the database - Index 2

        // Dequeue people in the line
        while (!lineOfPeople.isEmpty()) {
            Machine.start(lineOfPeople.remove());
            try {
                System.out.println("********** Thank you for riding with the MTA! **********");
                Thread.sleep(3000);
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println("Oops! Something went wrong. Please try again at a later time.");
            }
        }
    }
}
