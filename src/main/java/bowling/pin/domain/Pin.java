package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;

import java.util.Objects;

public class Pin {

    public static final int initPins = 10;

    private Ball ball;
    private int remainingPins;
    private State state;

    public Pin(Ball ball) {
        this.ball = ball;
    }

    private Pin(State state, Ball ball) {
        this.ball = ball;
        this.remainingPins = initPins - ball.getPoint();
        this.state = state;
    }

    private Pin(State state, Ball ball, int remainingPins) {
        this.ball = ball;
        this.remainingPins = remainingPins;
        this.state = state;
    }

    public static Pin toStrike(Ball pitchPoint, int remainingPins) {
        return new Pin(State.STRIKE, pitchPoint, remainingPins);
    }

    public static Pin toSpare(Ball point, int remainingPins) {
        return new Pin(State.SPARE, point, remainingPins);
    }

    public static Pin toMiss(Ball point, int remainingPins) {
        return new Pin(State.MISS, point, remainingPins);
    }

    public static Pin toGutter(Ball point, int remainingPins) {
        return new Pin(State.GUTTER, point, remainingPins);
    }


    public int getRemainingPins() {
        return remainingPins;
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return remainingPins == pin.remainingPins &&
                Objects.equals(ball, pin.ball) &&
                state == pin.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ball, remainingPins, state);
    }

    @Override
    public String toString() {
        return "Pin{" +
                "ball=" + ball +
                ", remainingPins=" + remainingPins +
                ", state=" + state +
                '}';
    }
}
