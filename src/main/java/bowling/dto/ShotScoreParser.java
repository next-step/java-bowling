package bowling.dto;

import bowling.domain.ScoreType;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

enum ShotScoreParser {
    STRIKE(ScoreType.STRIKE, score -> "X"),
    SPARE(ScoreType.SPARE, score -> "/"),
    GUTTER(ScoreType.GUTTER, score -> "-"),
    MISS(ScoreType.MISS, score -> Integer.toString(score));

    private final ScoreType scoreType;
    private final Function<Integer, String> scoreToStringFunction;

    private static final Map<ScoreType, ShotScoreParser> shotScoreDtoMap = Arrays.stream(values())
            .collect(Collectors.toMap(v -> v.scoreType, Function.identity()));

    ShotScoreParser(ScoreType scoreType, Function<Integer, String> scoreToStringFunction) {
        this.scoreType = scoreType;
        this.scoreToStringFunction = scoreToStringFunction;
    }

    public static ShotScoreParser of(ScoreType scoreType) {
        return shotScoreDtoMap.get(scoreType);
    }

    String parse(Integer score) {
        return scoreToStringFunction.apply(score)
                .replace("null", "");
    }
}
