package step2.domain;

import step2.type.ResultPitchesType;

import java.util.Arrays;
import java.util.stream.Stream;

import static step2.type.ResultPitchesType.*;

public class BowlingSymbol {
    public static final String NO_MARK = "";
    public static final String TUPLE_FORM = "%s|%s";
    public static final String WALL_DELIMITER = "|";
    public static final String SPARE_MARK = "/";
    public static final String STRIKE_MARK = "X";
    public static final String GUTTER_MARK = "-";
    public static final int STRIKE_VALUE = 10;
    public static final int GUTTER_VALUE = 0;
    public static final BowlingSymbol DEFAULT_SYMBOL = new BowlingSymbol(NO_MARK);
    public static final String ERROR_INVALID_VALUE = "유효하지 않은 값입니다.";

    private final String symbol;

    public BowlingSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Builder Builder(int maxPitches, int currentSize) {
        return new Builder(maxPitches, currentSize);
    }

    public static class Builder {
        private final int maxPitches;
        private final int currentSize;
        private int firstPoint;
        private int secondPoint;
        private int thirdPoint;

        public Builder(int maxPitches, int currentSize) {
            this.maxPitches = maxPitches;
            this.currentSize = currentSize;
        }

        public Builder firstPoint(int firstPoint) {
            this.firstPoint = firstPoint;
            return this;
        }

        public Builder secondPoint(int secondPoint) {
            this.secondPoint = secondPoint;
            return this;
        }

        public Builder thirdPoint(int thirdPoint) {
            this.thirdPoint = thirdPoint;
            return this;
        }

        public BowlingSymbol build() {
            return BowlingSymbol.of(this);
        }

    }

    private static BowlingSymbol of(Builder builder) {
        if (isEmpty(builder.maxPitches) || isEmpty(builder.currentSize)) {
            return DEFAULT_SYMBOL;
        }

        if (builder.maxPitches == FinalFrame.MAX_PITCHES) {
            return getFinalFrameSymbol(builder);
        }

        return getNormalFrameSymbol(builder);
    }

    private static BowlingSymbol getFinalFrameSymbol(Builder builder) {
        int firstPoint = builder.firstPoint;
        int secondPoint = builder.secondPoint;
        int thirdPoint = builder.thirdPoint;
        int currentSize = builder.currentSize;
        ResultPitchesType type = ResultPitchesType.getType(firstPoint, secondPoint);

        isValid(builder);

        String symbol = pointToString(firstPoint);

        if (currentSize >= 2) {
            symbol += WALL_DELIMITER + pointToString(firstPoint, secondPoint);
        }

        if (currentSize >= 3 && Arrays.asList(DOUBLE, STRIKE, SPARE).contains(type)) {
            symbol += WALL_DELIMITER + pointToString(calcPrevPoint(firstPoint, secondPoint), thirdPoint);
        }

        return new BowlingSymbol(symbol);
    }

    private static int calcPrevPoint(int firstPoint, int secondPoint) {
        if (isStrike(firstPoint) && !isStrike(secondPoint)) {
            return secondPoint;
        }
        return Integer.sum(firstPoint, secondPoint);
    }

    private static void isValid(Builder builder) {
        if (builder.firstPoint + builder.secondPoint < STRIKE_VALUE && builder.thirdPoint > GUTTER_VALUE) {
            throw new IllegalArgumentException(ERROR_INVALID_VALUE);
        }
    }

    private static boolean isStrike(int firstPoint) {
        return firstPoint == STRIKE_VALUE;
    }

    private static boolean isSpare(int firstPoint, int secondPoint) {
        return firstPoint != STRIKE_VALUE && firstPoint + secondPoint == STRIKE_VALUE;
    }

    private static BowlingSymbol getNormalFrameSymbol(Builder builder) {
        int firstPoint = builder.firstPoint;
        int secondPoint = builder.secondPoint;

        if (isEmpty(builder.currentSize)) {
            return DEFAULT_SYMBOL;
        }

        if (isStrike(firstPoint) || builder.currentSize == 1) {
            return new BowlingSymbol(pointToString(firstPoint));
        }
        if (isSpare(firstPoint, secondPoint)) {
            return new BowlingSymbol(String.format(TUPLE_FORM, pointToString(firstPoint), SPARE_MARK));
        }
        return new BowlingSymbol(String.format(TUPLE_FORM, pointToString(firstPoint), pointToString(secondPoint)));
    }

    private static String pointToString(int point) {
        if (point == STRIKE_VALUE) {
            return STRIKE_MARK;
        }
        if (point == GUTTER_VALUE) {
            return GUTTER_MARK;
        }
        return String.valueOf(point);
    }


    private static String pointToString(int first, int second) {
        if (!isStrike(first) && isStrike(Integer.sum(first, second))) {
            return SPARE_MARK;
        }
        return pointToString(second);
    }


    private static boolean isEmpty(int size) {
        return size == 0;
    }
}
