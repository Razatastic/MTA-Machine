package MTA;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        // TEST CASE 1 - John has $50 and doesn't own a metro card. He wants to purchase one and fill it with $10.
        Person John = new Person(50, 0);
        // TEST CASE 2 - Mary already has a metro card and simply wants to check its balance. She has no money.
        Person Mary = new Person(0);
        // Create a line of people waiting for the machine
        Queue<Person> lineOfPeople = new LinkedList<>();
        lineOfPeople.add(John);
        lineOfPeople.add(Mary);

        Machine.addCard(); // Add a card with no money to the database
        Machine.addCard(4.32); // Add a card with $4.32 to the database
        Machine mtaMachine = new Machine();
        mtaMachine.getUserInput(lineOfPeople.remove());
    }
}
