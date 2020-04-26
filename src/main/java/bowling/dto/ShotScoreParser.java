package bowling.dto;

import bowling.domain.scoreType.ScoreType;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Function;

enum ShotScoreParser {
    STRIKE(EnumSet.of(ScoreType.STRIKE), score -> "X"),
    SPARE(EnumSet.of(ScoreType.SPARE), score -> "/"),
    GUTTER(EnumSet.of(ScoreType.GUTTER_FIRST, ScoreType.GUTTER_SECOND), score -> "-"),
    MISS(EnumSet.of(ScoreType.MISS_FIRST, ScoreType.MISS_SECOND), score -> Integer.toString(score));

    private final EnumSet<ScoreType> scoreType;
    private final Function<Integer, String> scoreToStringFunction;

    ShotScoreParser(EnumSet<ScoreType> scoreType, Function<Integer, String> scoreToStringFunction) {
        this.scoreType = scoreType;
        this.scoreToStringFunction = scoreToStringFunction;
    }

    public static ShotScoreParser of(ScoreType scoreType) {
        return Arrays.stream(values())
                .filter(v -> v.scoreType.contains(scoreType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("ShotScoreParser of failed. not matching score type : argument scoreType=%s", scoreType)));
    }

    String parse(Integer score) {
        return scoreToStringFunction.apply(score)
                .replace("null", "");
    }
}
