package bowling.domain.score;

import bowling.exception.InvalidTurnScoreException;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TurnScore extends Score {
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;
    private static final Map<Integer, TurnScore> CACHED =
            IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                    .boxed()
                    .collect(
                            Collectors.toMap(
                                    iValue -> iValue,
                                    TurnScore::new)
                    );

    private TurnScore(final int score) {
        super(score);
    }

    public static TurnScore empty() {
        return InnerLazyClass.EMPTY_TURN_SCORE;
    }

    public boolean isAllClear() {
        return score == MAX_VALUE;
    }

    public static TurnScore of(final int score) {
        if (!CACHED.containsKey(score)) {
            throw new InvalidTurnScoreException();
        }
        return CACHED.get(score);
    }

    public static TurnScore of(final Score score) {
        return of(score.value());
    }

    private static class InnerLazyClass {
        private static final TurnScore EMPTY_TURN_SCORE = new TurnScore(0) {
            @Override
            public boolean isEmpty() {
                return true;
            }
        };
    }
}
