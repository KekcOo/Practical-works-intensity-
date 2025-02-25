package observer.without;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel {
    private List<String> subscribers = new ArrayList<>();
    private String channelName;

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void addSubscriber(String name) {
        subscribers.add(name);
    }



    public void publishVideo(String videoTitle) {
        System.out.println(channelName + " опубликовал: " + videoTitle);
        notifySubscribers(videoTitle);
    }


    private void notifySubscribers(String videoTitle) {
        for (String subscriber : subscribers) {
            System.out.println(subscriber + " получено уведомление: Новое видео: " + videoTitle);
        }
    }




    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("System of Down");

        channel.addSubscriber("Alice");
        channel.addSubscriber("Bob");


        channel.publishVideo("We are back");
    }
}
