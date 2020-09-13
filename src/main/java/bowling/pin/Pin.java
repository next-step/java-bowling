package bowling.pin;

import bowling.ball.Ball;
import bowling.state.State;

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
    private String state;

    private Pin(Ball ball, int remainingPins, String state) {
        this.ball = ball;
        this.remainingPins = remainingPins;
        this.state = state;
    }

    public static Pin eachPitch(List<Pin> pins, Ball ball) {
        int remainingPins = getRemainingPins(pins, ball);
        String state = pitchResultState(pins, ball);
        return new Pin(ball, remainingPins, state);
    }

    public static String pitchResultState(List<Pin> pins, Ball ball) {
        int remainingPins = getRemainingPins(pins, ball);
        int pitchCount = ball.getPitchNumber();

        if (pitchCount == FIRST_PITCH && remainingPins == CLEAR_PINS) {
            return State.STRIKE.toString();
        }
        if (pitchCount == SECOND_PITCH && remainingPins == CLEAR_PINS) {
            return getSecondPitchResult(pins);
        }

        if (pitchCount == FIRST_PITCH && remainingPins == Pin.INIT_PINS) {
            return State.GUTTER.toString();
        }

        if (pitchCount == SECOND_PITCH && remainingPins == pins.get(FIRST_PITCH_INDEX).getRemainingPins()) {
            return State.GUTTER.toString();
        }

        return String.valueOf(ball.getPoint());
    }

    private static String getSecondPitchResult(List<Pin> pins) {
        String firstPitchState = pins.get(FIRST_PITCH_INDEX).getState();
        if (firstPitchState.equals(State.STRIKE.toString())) {
            return " ";
        }
        return State.SPARE.toString();
    }

    public String getState() {
        return state;
    }

    private static int getRemainingPins(List<Pin> pins, Ball ball) {
        if (pins.size() != NUMBER_ZERO) {
            int remainingPins = pins.get(NUMBER_ZERO).getRemainingPins();
            return remainingPins - ball.getPoint();
        }
        return Pin.INIT_PINS - ball.getPoint();
    }

    public int getRemainingPins() {
        return remainingPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return remainingPins == pin.remainingPins &&
                Objects.equals(ball, pin.ball) &&
                Objects.equals(state, pin.state);
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
                ", states='" + state + '\'' +
                '}';
    }
}
