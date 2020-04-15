package bowling.domain;

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
}
