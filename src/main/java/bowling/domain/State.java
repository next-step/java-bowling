package bowling.domain;

import bowling.exception.BowlingException;

import java.util.function.Function;

public enum State {

    READY((count) -> count),
    STRIKE((count) -> "X"),
    SPARE((count) -> "/"),
    MISS((count) -> count),
    GUTTER((count) -> "-");

    private static final String MISS_STATE = "알수 없는 상태의 경우 발생";

    private Function<Integer, String> showStateDisplay;

    State(Function showStateDisplay) {
        this.showStateDisplay = showStateDisplay;
    }

    public String showStateDisplay(int count) {
        return String.valueOf(showStateDisplay.apply(count));
    }

    public static State checkStatue(Tern tern, int pinCount) {
        if (tern == Tern.FIRST && pinCount == 0) {
            return State.STRIKE;
        }

        if (tern == Tern.SECOND && pinCount == 0) {
            return State.SPARE;
        }

        if (tern == Tern.SECOND && pinCount == 10) {
            return State.GUTTER;
        }

        if ((tern == Tern.FIRST || tern == Tern.SECOND)
                && (pinCount < 10 && pinCount > 0)) {
            return State.MISS;
        }

        throw new BowlingException(MISS_STATE);
    }
}
