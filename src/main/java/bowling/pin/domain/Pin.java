package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;

import java.util.List;
import java.util.Objects;

public class Pin {

    public static final int initPins = 10;

    private Ball ball; // 던진 포인트와 pitch 횟수
    private int remainingPins;
    private State state;

    private Pin(Ball ball, int remainingPins, State state) {
        this.ball = ball;
        this.remainingPins = remainingPins;
        this.state = state;
    }

    public static Pin pitchResult(List<Pin> pins, Ball ball) {
        int remainingPins = getRemainingPins(pins, ball);
        if (ball.getPitchNumber() == 1 && remainingPins == 0) { // 1회차 스트라이크
            return Pin.toStrike(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == 0) { // 2회차 스페어
            if (pins.get(0).getState().equals(State.STRIKE)) {
                return Pin.toPass(ball, remainingPins);
            }
            return Pin.toSpare(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 1 && remainingPins == Pin.initPins) { // 1회차 거터
            return Pin.toGutter(ball, remainingPins);
        }

        if (ball.getPitchNumber() == 2 && remainingPins == pins.get(0).getRemainingPins()) { // 2회차 거터
            return Pin.toGutter(ball, remainingPins);
        }

        return Pin.toMiss(ball, remainingPins); // 핀이 낱개로 남음
    }

    private static int getRemainingPins(List<Pin> pins, Ball ball) {
        if (pins.size() != 0) {
            return pins.get(0).getRemainingPins() - ball.getPoint();
        }
        return Pin.initPins - ball.getPoint();
    }

    private static Pin toStrike(Ball ball, int remainingPins) {
        return new Pin(ball, remainingPins, State.STRIKE);
    }

    private static Pin toSpare(Ball ball, int remainingPins) {
        return new Pin(ball, remainingPins, State.SPARE);
    }

    private static Pin toMiss(Ball ball, int remainingPins) {
        return new Pin(ball, remainingPins, State.MISS);
    }

    private static Pin toGutter(Ball ball, int remainingPins) {
        return new Pin(ball, remainingPins, State.GUTTER);
    }

    private static Pin toPass(Ball ball, int remainingPins) {
        return new Pin(ball, remainingPins, State.PASS);
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
