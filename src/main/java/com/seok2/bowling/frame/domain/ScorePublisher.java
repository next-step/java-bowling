package com.seok2.bowling.frame.domain;

import com.seok2.bowling.pin.domain.Pin;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ScorePublisher {

    private List<Observer> observers = new CopyOnWriteArrayList<>();

    protected void subscribe(Observer observer) {
        observers.add(observer);
    }

    protected void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    protected void update(Pin felled) {
        observers.forEach(o -> o.update(felled.toScore()));
    }

    protected int size() {
        return observers.size();
    }

}
