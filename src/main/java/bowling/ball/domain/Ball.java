package bowling.ball.domain;

import bowling.global.exception.InputPitchPointNullPointerException;
import bowling.global.exception.OutOfPitchRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.global.utils.StringParser;

import java.util.Objects;

public class Ball {

    private int point;
    private int pitchNumber = 1;


    private Ball(String point) {
        validateInputPitchPointIsNull(point);
        this.point = new StringParser(point).toInt();
        validateOutofPitchRange();
        pitchNumber += 1;
    }

    public static Ball pitch(String point) {
        return new Ball(point);
    }

    private void validateInputPitchPointIsNull(String point) {
        if (point == null || point.trim().isEmpty()) {
            throw new InputPitchPointNullPointerException(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
        }
    }

    private void validateOutofPitchRange() {
        if (point < 0 || point > 10) {
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
        return point == ball.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

}
