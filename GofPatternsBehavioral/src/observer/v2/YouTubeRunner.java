package observer.v2;

public class YouTubeRunner {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("Kyplinov");
        Observer user1 = new Subscriber("Vasy");
        Observer user2 = new Subscriber("Pety");

        channel.subscribe(user1);
        channel.subscribe(user2);

        channel.publishVideo("Phasmaphobia");

        channel.unsubscribe(user1);

        channel.publishVideo("Amnesia");
    }
}
