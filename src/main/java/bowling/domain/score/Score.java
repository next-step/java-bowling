package bowling.domain.score;

import bowling.domain.Symbol;

import java.util.Optional;

public class Score {
    public static final int PINS_ZERO = 0;
    public static final String BLANK = "";

    private final Pins first;
    private Pins second;

    public Score(Pins first) {
        this.first = first;
        this.second = new Pins(PINS_ZERO);
    }

    public Score() {
        this.first = new Pins(PINS_ZERO);
        this.second = new Pins(PINS_ZERO);
    }

    public Pins getFirst() {
        return first;
    }

    public void setSecond(Pins second) {
        this.second = second;
    }

    public Pins getSecond() {
        return second;
    }

    public boolean isStrike() {
        return this.first.get() == Symbol.STRIKE.getPins().get();
    }

    public boolean isSpare(Pins pins) {
        return this.first.get() + pins.get() == Symbol.SPARE.getPins().get();
    }

    public boolean isGutter() {
        return this.first.get() == Symbol.GUTTER.getPins().get();
    }

    public boolean isAllGutter(Pins secondPins) {
        return this.first.get() == Symbol.GUTTER.getPins().get()
                && secondPins.get() == Symbol.GUTTER.getPins().get();
    }

    public int getFrameScore() {
        Pins secondPins = Optional.ofNullable(this.second).orElse(new Pins(PINS_ZERO));
        return this.first.get() + secondPins.get();
    }

    @Override
    public String toString() {
        int sum = first.get() + second.get();
        return sum != PINS_ZERO ? String.valueOf(sum) : BLANK;
    }
}
