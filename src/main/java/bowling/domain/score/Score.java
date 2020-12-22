package bowling.domain.score;

import bowling.domain.Symbol;

import java.util.Optional;

public class Score {
    private final Pins first;
    private Pins second;

    public Score(Pins first) {
        this.first = first;
        this.second = new Pins(0);
    }

    public Score() {
        this.first = new Pins(0);
        this.second = new Pins(0);
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
        return this.first.get() + pins.get()
                == Symbol.SPARE.getPins().get();
    }

    public boolean isGutter() {
        return this.first.get() == Symbol.GUTTER.getPins().get();
    }

    public boolean isAllGutter() {
        return this.first.get() == Symbol.GUTTER.getPins().get()
                && this.second.get() == Symbol.GUTTER.getPins().get();
    }

    public boolean isFirst() {
        return this.second == null;
    }

    public int getFrameScore() {
        Pins secondPins = Optional.ofNullable(this.second).orElse(new Pins(0));
        return this.first.get() + secondPins.get();
    }

    @Override
    public String toString() {
        int sum = first.get() + second.get();
        return sum != 0 ? String.valueOf(sum) : "";
    }
}
