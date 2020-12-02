package bowling.domain;

import java.util.LinkedList;
import java.util.List;

abstract class Subject<T> {
    private final List<Observer<T>> observers = new LinkedList<>();

    void register(Observer<T> observer) {
        observers.add(observer);
    }

    void register(Runnable runnable) { register(t -> runnable.run()); }

    void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    abstract T get();

    abstract void execute();
}
