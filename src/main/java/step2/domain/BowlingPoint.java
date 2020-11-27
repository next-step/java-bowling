package step2.domain;

import java.util.Objects;

public class BowlingPoint {
    public static final String NO_MARK = "";
    public static final String INVALID_PITCHES_COUNT = "유효하지 않은 투구수입니다.";
    public static final String STRIKE_MARK = "X";
    public static final String GUTTER_MARK = "-";
    public static final String SPARE_MARK = "/";
    public static final int PIN_MINIMUM_RANGE = 0;
    public static final int PIN_MAXIMUM_RANGE = 10;

    private final int point;
    private final String mark;
    private final boolean completed;

    public BowlingPoint(int point, String mark, boolean completed) {
        isValidPoint(point);
        this.point = point;
        this.mark = mark;
        this.completed = completed;
    }

    public static BowlingPoint of() {
        return new BowlingPoint(0, NO_MARK, false);
    }

    public static BowlingPoint of(int point) {
        return new BowlingPoint(point, pointToMark(point), true);
    }

    public static BowlingPoint of(int current, int prev) {
        return new BowlingPoint(current, pointToMark(current, prev), true);
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
            throw new IllegalArgumentException(INVALID_PITCHES_COUNT);
        }
    }

    public String getMark() {
        return mark;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingPoint that = (BowlingPoint) o;
        return point == that.point &&
                completed == that.completed &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, mark, completed);
    }
}
