package bowling.domain.frame;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Round {
    private static final int FIRST_VALUE = 1;
    private static final int LAST_VALUE = 10;
    private static final int NEXT_ROUND_VALUE = 1;
    private static final int CALC_INT_TO_ROUND_ARRAY = 1;
    private static final String NO_RANGE_EXCEPTION_MESSAGE_FORMAT = "Round는 %d ~ %d 만 존재합니다.";
    private static final Round[] ONE_THROUGH_TEN = {
            new Round(1),
            new Round(2),
            new Round(3),
            new Round(4),
            new Round(5),
            new Round(6),
            new Round(7),
            new Round(8),
            new Round(9),
            new Round(10)
    };
    public static final Round FIRST = ONE_THROUGH_TEN[0];
    public static final Round LAST = ONE_THROUGH_TEN[9];

    private final int value;

    private Round(int value) {
        this.value = value;
    }

    public static Round from(int value) {
        validRound(value);
        return ONE_THROUGH_TEN[value - CALC_INT_TO_ROUND_ARRAY];
    }

    private static void validRound(int value) {
        if (value < FIRST_VALUE || value > LAST_VALUE) {
            throw new IllegalArgumentException(String.format(NO_RANGE_EXCEPTION_MESSAGE_FORMAT, FIRST_VALUE, LAST_VALUE));
        }
    }

    public static List<Round> allRounds() {
        return Arrays.asList(ONE_THROUGH_TEN);
    }

    public Round nextRound() {
        return from(this.value + NEXT_ROUND_VALUE);
    }

    public boolean isLast() {
        return this.value == LAST_VALUE;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Round round1 = (Round) o;
        return value == round1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Round{" +
                "value=" + value +
                '}';
    }
}
