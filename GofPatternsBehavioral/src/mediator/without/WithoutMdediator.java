package mediator.without;

public class WithoutMdediator {

    public static void main(String[] args) {
        BadUser user1 = new BadUser("Alice");
        BadUser user2 = new BadUser("Bob");
        BadUser user3 = new BadUser("Charlie");


        user1.addUser(user2);
        user1.addUser(user3);

        user2.addUser(user1);
        user2.addUser(user3);

        user3.addUser(user1);
        user3.addUser(user2);


        user1.send("Hello, everyone!");
    }
}
