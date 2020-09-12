package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;

import java.util.Objects;

public class Pin {

    public static final int initPins = 10;

    // 역할 : Pin들을 관리, 10개 치면 남은 핀 확인, 남은 핀에 따라 상태 관리
    private Ball ball;
    private int remainingPins; // 남은핀
    private State state;

    public Pin(Ball ball) {
        this.remainingPins = initPins - ball.getPoint();
        this.ball = ball;
    }

    private Pin(State state, Ball ball) { // firstPitch
        this.remainingPins = initPins - ball.getPoint();
        this.state = state;
    }

    private Pin(State state, int point) { // secondPitch
        this.remainingPins = point;
        this.state = state;
    }

    public static Pin firstPitch(Ball pitchPoint) {
        return new Pin(pitchPoint);
    }

    public static Pin secondPitch(Ball pitchPoint) {
        return new Pin(pitchPoint);
    }

    public static Pin toStrike(Ball pitchPoint) {
        return new Pin(State.STRIKE, pitchPoint);
    }

    public static Pin toSpare(Ball point) {
        return new Pin(State.SPARE, point);
    }

    public static Pin toMiss(Ball point) {
        return new Pin(State.MISS, point);
    }

    public static Pin toGutter(Ball point) {
        return new Pin(State.GUTTER, point);
    }

    public static Pin pitchResult(int point, Ball ball) {
        int remainingPins = initPins - point;
        if (ball.getPitchNumber() == 1 && remainingPins == 0) {
            return Pin.toStrike(ball);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == 0) {
            return Pin.toSpare(ball);
        }

        if (ball.getPitchNumber() == 1 && remainingPins == initPins) {
            return Pin.toGutter(ball);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == point) {
            return Pin.toGutter(ball);
        }

        return Pin.toMiss(ball);
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
