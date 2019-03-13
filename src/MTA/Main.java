package MTA;

import java.util.HashMap;

public class Main extends Thread {
    public static void main(String[] args) {
        HashMap<Integer, Card> mtaDatabase = new HashMap<>();
        mtaDatabase.put(100000, new Card());

        Person Mary = new Person(20, new Card());
        Person John = new Person(10);
        Machine mtaMachine = new Machine(John);
        mtaMachine.newUser(Mary);
    }
}
