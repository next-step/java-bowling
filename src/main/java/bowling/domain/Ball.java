package bowling.domain;

import java.util.Objects;

public class Ball {
    public static final int MIN_PIN_COUNT = 0;
    public static final int MAX_PIN_COUNT = 10;

    private final Pin pin;
    private final State state;

    private Ball(Pin pin, State state) {
        this.pin = pin;
        this.state = state;
    }

    public static Ball first() {
        return new Ball(Pin.first(), State.READY);
    }

    public static Ball of(int fallenPinCount, State state) {
        return new Ball(Pin.of(fallenPinCount), state);
    }
    public static Ball of(int fallenPinCount) {
        return new Ball(Pin.of(fallenPinCount), State.READY);
    }

    public int getFallenPinCount() {
        return pin.getFallenPinCount();
    }

    public boolean isReady() { return state == State.READY; }

    public String symbol() {
        return state.getSymbol(pin);
    }

    public Ball bowl(Ball ball) {
        return new Ball(ball.pin, state.bowl(this.pin, ball.pin));
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball bowl = (Ball) o;
        return Objects.equals(pin, bowl.pin) && state == bowl.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin, state);
    }
}
