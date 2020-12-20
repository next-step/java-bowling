package bowling.domain;

import java.util.Optional;

public class Score {
    public static final int PINS_NOT_GUTTER = 1;
    private final Pins first;
    private Pins second;

    public Score(Pins first) {
        this.first = first;
    }

    public Pins getFirst() {
        return first;
    }

    public void setSecond(Pins second) {
        if (isStrike()) {
            throw new IllegalStateException("첫 구가 스트라이트");
        }
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
        return this.first.get() == Symbol.GUTTER.getPins().get()
                || Optional.ofNullable(this.second).orElse(new Pins(PINS_NOT_GUTTER)).get() == Symbol.GUTTER.getPins().get();
    }

    public boolean isFirst() {
        return this.second == null;
    }
}
