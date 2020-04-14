package bowling.domain;

import bowling.domain.turn.Turn;
import bowling.exception.BowlingException;

import java.util.function.Function;

public enum Result {

    READY((count) -> count),
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

    public static Result checkStatue(Turn turn, int pinCount) {
        if (turn == Turn.FIRST && pinCount == 0) {
            return Result.STRIKE;
        }

        if (turn == Turn.SECOND && pinCount == 0) {
            return Result.SPARE;
        }

        if (turn == Turn.SECOND && pinCount == 10) {
            return Result.GUTTER;
        }

        if ((turn == Turn.FIRST || turn == Turn.SECOND)
                && (pinCount < 10 && pinCount > 0)) {
            return Result.MISS;
        }

        throw new BowlingException(MISS_STATE);
    }
}
