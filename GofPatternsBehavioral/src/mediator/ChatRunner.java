package mediator;

public class ChatRunner {
    public static void main(String[] args) {
       ChatMediator chat = new ChatRoom();


        User user1 = new ChatUser(chat, "Vasy");
        User user2 = new ChatUser(chat, "Pety");
        User user3 = new ChatUser(chat, "Katy");

        chat.addUser(user1);
        chat.addUser(user2);
        chat.addUser(user3);

        user1.send("Hello World!!!");
    }
}
