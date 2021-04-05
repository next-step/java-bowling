package bowling.domain;

public class Frame {
    private final Pins pins;

    private Frame(Pins pins) {
        this.pins = pins;
    }

    public static Frame of(int countOfDownPin, Pins pins) {
        pins.first(countOfDownPin);
        return new Frame(pins);
    }

    public Frame next(int countOfDownPin) {
        return new Frame(pins.next(countOfDownPin));
    }

    public boolean isEnd() {
        return pins.isEnd();
    }

    public Pins pins() {
        return pins;
    };

    public int score() {
        return pins.score();
    }

    public int bonusChance() {
        return pins.bonusChance();
    }
}
