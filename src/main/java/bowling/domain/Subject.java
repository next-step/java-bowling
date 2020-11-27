package bowling.domain;

import java.util.List;

abstract class Subject<T> {
    private List<Observer> observers;

    void register(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers() {
        observers.stream()
                .forEach(observer -> observer.update(this));
    }

    abstract T get();

    abstract void execute();
}
