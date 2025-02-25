package mediator.without;

import java.util.ArrayList;
import java.util.List;

public class BadUser {
    private String name;
    private List<BadUser> users = new ArrayList<>();

    public BadUser(String name) {
        this.name = name;
    }

    public void addUser(BadUser user) {
        users.add(user);
    }

    public void send(String message) {
        System.out.println(name + " sends: " + message);
        for (BadUser user : users) {
            if (user != this) {
                user.receive(message);
            }
        }
    }

    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}
