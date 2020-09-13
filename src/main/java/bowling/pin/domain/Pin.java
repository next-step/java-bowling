package bowling.pin.domain;

import bowling.ball.domain.Ball;
import bowling.state.domain.State;

import java.util.List;
import java.util.Objects;

import static bowling.global.utils.CommonConstant.NUMBER_ZERO;

public class Pin {

    public static final int INIT_PINS = 10;
    private static final int FIRST_PITCH = 1;
    private static final int SECOND_PITCH = 2;
    private static final int CLEAR_PINS = 0;
    private static final int FIRST_PITCH_INDEX = 0;

    private Ball ball;
    private int remainingPins;
    private State state;

    private Pin(Ball ball, int remainingPins, State state) {
        this.ball = ball;
        this.remainingPins = remainingPins;
        this.state = state;
    }

    public static Pin pitchResult(List<Pin> pins, Ball ball) {
        int remainingPins = getRemainingPins(pins, ball);
        int pitchCount = ball.getPitchNumber();
        int firstRemainingPins = pins.get(FIRST_PITCH_INDEX).getRemainingPins();

        if (pitchCount == FIRST_PITCH && remainingPins == CLEAR_PINS) {
            return Pin.toStrike(ball, remainingPins);
        }
        if (pitchCount == SECOND_PITCH && remainingPins == CLEAR_PINS) {
            State firstPitchState = pins.get(FIRST_PITCH_INDEX).getState();
            if (firstPitchState.equals(State.STRIKE)) {
                return Pin.toPass(ball, remainingPins);
            }
            return Pin.toSpare(ball, remainingPins);
        }

        if (pitchCount == FIRST_PITCH && remainingPins == Pin.INIT_PINS) {
            return Pin.toGutter(ball, remainingPins);
        }

        if (pitchCount == SECOND_PITCH && remainingPins == firstRemainingPins) {
            return Pin.toGutter(ball, remainingPins);
        }

        return Pin.toMiss(ball, remainingPins);
    }

    private static int getRemainingPins(List<Pin> pins, Ball ball) {
        if (pins.size() != NUMBER_ZERO) {
            int remainingPins = pins.get(NUMBER_ZERO).getRemainingPins();
            return remainingPins - ball.getPoint();
        }
        return Pin.INIT_PINS - ball.getPoint();
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
