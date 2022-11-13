package bowling.domain;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum Result {
    STRIKE((frame, index) -> frame.get(index).isStrike()),
    SPARE((frame, index) -> index > 0 && frame.sumNowAndBeforePinCount(index) == 10),
    NONE((frame, index) -> frame.get(index).isNone()),
    NORMAL((frame, index) -> false);

    private final BiFunction<Frame, Integer, Boolean> match;

    Result(BiFunction<Frame, Integer, Boolean> match) {
        this.match = match;
    }

    public static Result valueOf(Frame frame, int index) {
        return Stream.of(values())
                .filter(r -> r.match(frame, index))
                .findAny()
                .orElse(NORMAL);
    }

    private Boolean match(Frame frame, int index) {
        return match.apply(frame, index);
    }
}
