package bowling.refactoring.domain;


import java.util.*;

public class Score {
    private final List<Pins> pinsList;

    public Score() {
        this.pinsList = new ArrayList<>();
    }

    public void bowl(int fallenPinCount) {
        Pins pins = new Pins(fallenPinCount);
        this.pinsList.add(pins);
    }

    public boolean isStrike() {
        return this.pinsList.size() == 1 && this.pinsList.get(0).isStrike();
    }

    public boolean isSpare() {
        return this.pinsList.size() == 2 && this.pinsList.stream().mapToInt(Pins::count).sum() == 10;
    }

    public Pins firstScore() {
        return this.pinsList.get(0);
    }

    public Pins secondScore() {
        return this.pinsList.get(1);
    }

    public Pins bonusScore() {
        return this.pinsList.get(2);
    }

    public List<Pins> pinsList(){
        return new ArrayList<>(pinsList);
    }
}

