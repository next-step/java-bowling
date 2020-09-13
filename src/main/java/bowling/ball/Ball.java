package bowling.ball;

import bowling.global.exception.InputPitchPointNullPointerException;
import bowling.global.exception.OutOfPitchRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.global.utils.StringParser;

import java.util.Objects;

public class Ball {

    private static final int PITCHI_MIN_NUMBER = 0;
    private static final int PITCHI_MAX_NUMBER = 10;


    private int point;
    private int pitchNumber;

    private Ball(String point, int pitchNumber) {
        validateInputPitchPointIsNull(point);
        this.point = new StringParser(point).toInt();
        validateOutofPitchRange();
        this.pitchNumber = pitchNumber;
    }

    public static Ball pitch(String point, int pitchNumber) {
        return new Ball(point, pitchNumber);
    }

    private void validateInputPitchPointIsNull(String point) {
        if (point == null || point.trim().isEmpty()) {
            throw new InputPitchPointNullPointerException(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
        }
    }

    private void validateOutofPitchRange() {
        if (point < PITCHI_MIN_NUMBER || point > PITCHI_MAX_NUMBER) {
            throw new OutOfPitchRangeException(ExceptionMessage.INVALID_PITCH_RANGE);
        }
    }

    public int getPoint() {
        return point;
    }

    public int getPitchNumber() {
        return pitchNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return point == ball.point &&
                pitchNumber == ball.pitchNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, pitchNumber);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "point=" + point +
                ", pitchNumber=" + pitchNumber +
                '}';
    }
}
