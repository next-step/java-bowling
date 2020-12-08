package step3.domain;

import step3.exception.InvalidPitchesException;

import java.util.Objects;

public class BowlingPoint {
    public static final String ERROR_INVALID_PITCHES = "유효하지 않은 투구수입니다.";
    public static final int PIN_MINIMUM_RANGE = 0;
    public static final int PIN_MAXIMUM_RANGE = 10;
    public static final BowlingPoint EMPTY_BOWLING_POINT = new BowlingPoint(0);

    private final int point;

    public BowlingPoint(int point) {
        isValidPoint(point);
        this.point = point;
    }

    public static BowlingPoint of() {
        return new BowlingPoint(0);
    }

    public static BowlingPoint of(int point) {
        return new BowlingPoint(point);
    }

    public static BowlingPoint of(int current, int prev) throws InvalidPitchesException {
        isValidRange(current, prev);
        return new BowlingPoint(current);
    }

    private static void isValidRange(int current, int prev) {
        if (Integer.sum(current, prev) > 10) {
            throw new InvalidPitchesException(ERROR_INVALID_PITCHES);
        }
    }

    private void isValidPoint(int point) {
        if (PIN_MINIMUM_RANGE > point || point > PIN_MAXIMUM_RANGE) {
            throw new IllegalArgumentException(ERROR_INVALID_PITCHES);
        }
    }


    public int getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPoint point1 = (BowlingPoint) o;
        return point == point1.point;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }
}
