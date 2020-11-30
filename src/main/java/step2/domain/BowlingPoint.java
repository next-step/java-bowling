package step2.domain;

import step2.exception.InvalidPitchesException;

import java.util.Objects;

public class BowlingPoint {
    public static final String NO_MARK = "";
    public static final String ERROR_INVALID_PITCHES = "유효하지 않은 투구수입니다.";
    public static final String STRIKE_MARK = "X";
    public static final String GUTTER_MARK = "-";
    public static final String SPARE_MARK = "/";
    public static final int PIN_MINIMUM_RANGE = 0;
    public static final int PIN_MAXIMUM_RANGE = 10;
    public static final BowlingPoint EMPTY_BOWLING_POINT = new BowlingPoint(0, NO_MARK);

    private final int point;
    private final String mark;

    public BowlingPoint(int point, String mark) {
        isValidPoint(point);
        this.point = point;
        this.mark = mark;
    }

    public static BowlingPoint of() {
        return new BowlingPoint(0, NO_MARK);
    }

    public static BowlingPoint of(int point) {
        return new BowlingPoint(point, pointToMark(point));
    }

    public static BowlingPoint of(int current, int prev) throws InvalidPitchesException {
        isValidRange(current, prev);
        return new BowlingPoint(current, pointToMark(current, prev));
    }

    private static void isValidRange(int current, int prev) {
        if (Integer.sum(current, prev) > 10) {
            throw new InvalidPitchesException(ERROR_INVALID_PITCHES);
        }
    }

    private static String pointToMark(int point) {
        if (isStrike(point)) {
            return STRIKE_MARK;
        }
        if (isGutter(point)) {
            return GUTTER_MARK;
        }
        return String.valueOf(point);
    }

    private static String pointToMark(int current, int prev) {
        if (isStrike(prev) && isGutter(current)) {
            return NO_MARK;
        }
        if (isMiss(prev) && isStrike(current + prev)) {
            return SPARE_MARK;
        }

        return pointToMark(current);
    }

    private static boolean isMiss(int prev) {
        return prev < 10;
    }

    private static boolean isGutter(int point) {
        return point == 0;
    }

    private static boolean isStrike(int point) {
        return point == 10;
    }

    private void isValidPoint(int point) {
        if (PIN_MINIMUM_RANGE > point || point > PIN_MAXIMUM_RANGE) {
            throw new IllegalArgumentException(ERROR_INVALID_PITCHES);
        }
    }

    public String getMark() {
        return mark;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPoint point1 = (BowlingPoint) o;
        return point == point1.point &&
                Objects.equals(mark, point1.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, mark);
    }
}
