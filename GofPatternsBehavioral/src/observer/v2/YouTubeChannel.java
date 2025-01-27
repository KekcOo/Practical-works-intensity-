package observer.v2;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    private final String channelName;

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    public void publishVideo(String videoTitle) {
        System.out.println(channelName + " опубликовал: " + videoTitle);
        notifyObservers("Новое видео: " + videoTitle);
    }
}
