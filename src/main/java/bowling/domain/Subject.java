package bowling.domain;

import java.util.LinkedList;
import java.util.List;

abstract class Subject<T> {
    private final List<Observer> observers = new LinkedList<>();

    void register(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    abstract T get();

    abstract void execute();
}
