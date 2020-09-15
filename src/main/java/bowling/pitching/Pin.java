package bowling.pitching;

import bowling.ball.Ball;

import java.util.List;
import java.util.Objects;

import static bowling.global.utils.CommonConstant.NUMBER_ZERO;

// 삭제 예정
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

        if (remainingPins == CLEAR_PINS && pitchCount == 1) {
            return PitchingState.STRIKE.toString();
        }

        if (remainingPins == CLEAR_PINS) {
            return PitchingState.SPARE.toString();
        }

        if (ball.getPoint() == CLEAR_PINS) {
            return PitchingState.GUTTER.toString();
        }

        return String.valueOf(ball.getPoint());
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
