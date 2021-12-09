package bowling.domain.frame;

import java.util.Objects;

public class Round {

    private static final int FIRST_VALUE = 1;
    private static final int LAST_VALUE = 10;
    private static final int NEXT_ROUND_VALUE = 1;
    private static final int CALC_INT_TO_ROUND_ARRAY = 1;
    private static final String NO_RANGE_EXCEPTION_MESSAGE_FORMAT = "Round는 %d ~ %d 만 존재합니다.";
    private static final Round[] oneThroughTen = {
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
    public static final Round FIRST = oneThroughTen[0];
    public static final Round LAST = oneThroughTen[9];

    private final int round;

    private Round(int round) {
        this.round = round;
    }

    public static Round from(int round) {
        validRound(round);
        return oneThroughTen[round - CALC_INT_TO_ROUND_ARRAY];
    }

    private static void validRound(int round) {
        if (round < FIRST_VALUE || round > LAST_VALUE) {
            throw new IllegalArgumentException(String.format(NO_RANGE_EXCEPTION_MESSAGE_FORMAT, FIRST_VALUE, LAST_VALUE));
        }
    }

    public Round nextRound() {
        return from(this.round + NEXT_ROUND_VALUE);
    }

    public boolean isLast() {
        return this.round == LAST_VALUE;
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
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }
}
