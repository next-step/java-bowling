package bowling.domain.score;

import bowling.domain.roll.Roll;

import java.util.ArrayList;
import java.util.List;

public class ScoreManager {

    private final List<ScoreObserver> observers = new ArrayList<>();

    public void registerObserver(ScoreObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Roll roll) {
        observers.removeIf(observer -> !observer.update(roll));
    }

    public int observerSize() {
        return observers.size();
    }
}
