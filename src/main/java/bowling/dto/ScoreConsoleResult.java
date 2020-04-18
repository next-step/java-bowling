package bowling.dto;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ScoreConsoleResult {
    STRIKE(FrameScoreResult.STRIKE, score -> "X"),
    SPARE(FrameScoreResult.SPARE, score -> "/"),
    MISS(FrameScoreResult.MISS, Score::toString),
    GUTTER(FrameScoreResult.GUTTER, score -> "-");

    private final FrameScoreResult frameScoreResult;
    private final Function<Score, String> printFunction;

    private static final Map<FrameScoreResult, ScoreConsoleResult> CONSOLE_SCORES = Collections.unmodifiableMap(
            Stream.of(values())
                    .collect(Collectors.toMap(ScoreConsoleResult::getFrameScoreResult, Function.identity())));

    ScoreConsoleResult(final FrameScoreResult frameScoreResult, final Function<Score, String> printFunction) {
        this.frameScoreResult = frameScoreResult;
        this.printFunction = printFunction;
    }

    public static ScoreConsoleResult of(final FrameScoreResult frameScoreResult) {

        ScoreConsoleResult scoreConsoleResult = CONSOLE_SCORES.get(frameScoreResult);

        if (Objects.isNull(scoreConsoleResult)) {
            throw new IllegalArgumentException("FrameScoreResult not valid.");
        }

        return scoreConsoleResult;
    }

    private FrameScoreResult getFrameScoreResult() {
        return frameScoreResult;
    }

    public String toConsoleResult(final Score score) {
        return printFunction.apply(score);
    }
}
