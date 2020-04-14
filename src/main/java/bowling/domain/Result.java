package bowling.domain;

import bowling.domain.turn.TurnState;
import bowling.exception.BowlingException;

import java.util.function.Function;

public enum Result {

    STRIKE((count) -> "X"),
    SPARE((count) -> "/"),
    MISS((count) -> count),
    GUTTER((count) -> "-");

    private static final String MISS_STATE = "알수 없는 상태의 경우 발생";

    private Function<Integer, String> showStateDisplay;

    Result(Function showStateDisplay) {
        this.showStateDisplay = showStateDisplay;
    }

    public String showStateDisplay(int count) {
        return String.valueOf(showStateDisplay.apply(count));
    }

    public static Result getResultState(TurnState turnState, int pinCount) {
        if (turnState == TurnState.FIRST && pinCount == 0) {
            return Result.STRIKE;
        }

        if (turnState == TurnState.SECOND && pinCount == 0) {
            return Result.SPARE;
        }

        if (turnState == TurnState.SECOND && pinCount == 10) {
            return Result.GUTTER;
        }

        if ((turnState == TurnState.FIRST || turnState == TurnState.SECOND)
                && (pinCount < 10 && pinCount > 0)) {
            return Result.MISS;
        }

        throw new BowlingException(MISS_STATE);
    }
}
