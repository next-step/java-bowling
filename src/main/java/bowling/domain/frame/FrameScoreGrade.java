package bowling.domain.frame;

import bowling.domain.Turn;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum FrameScoreGrade {
    STRIKE((firstTurn, secondTurn) ->
            firstTurn.isAllClear()
    ),
    SPARE((firstTurn, secondTurn) ->
            firstTurn.union(secondTurn).isAllClear()
    ),
    MISS((firstTurn, secondTurn) ->
            firstTurn.isGutter() && secondTurn.isGutter()
    ),
    NORMAL((firstTurn, secondTurn) -> true),
    EMPTY((firstTurn, secondTurn) -> false);

    private final BiPredicate<Turn, Turn> selfCheckFunction;

    FrameScoreGrade(final BiPredicate<Turn, Turn> selfCheckFunction) {
        this.selfCheckFunction = selfCheckFunction;
    }

    public static FrameScoreGrade of(Turn firstTurn, Turn secondTurn) {
        //noinspection OptionalGetWithoutIsPresent
        return Arrays.stream(values())
                .filter(iFrameScore ->
                        iFrameScore.selfCheckFunction.test(firstTurn, secondTurn)
                )
                .findFirst().get();
    }
}
