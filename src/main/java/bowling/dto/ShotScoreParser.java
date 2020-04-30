package bowling.dto;

import bowling.domain.shot.type.ShotType;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.function.Function;

enum ShotScoreParser {
    STRIKE(EnumSet.of(ShotType.STRIKE), score -> "X"),
    SPARE(EnumSet.of(ShotType.SPARE), score -> "/"),
    GUTTER(EnumSet.of(ShotType.GUTTER_FIRST, ShotType.GUTTER_SECOND), score -> "-"),
    MISS(EnumSet.of(ShotType.MISS_FIRST, ShotType.MISS_SECOND), score -> Integer.toString(score));

    private final EnumSet<ShotType> shotType;
    private final Function<Integer, String> scoreToStringFunction;

    ShotScoreParser(EnumSet<ShotType> shotType, Function<Integer, String> scoreToStringFunction) {
        this.shotType = shotType;
        this.scoreToStringFunction = scoreToStringFunction;
    }

    public static ShotScoreParser of(ShotType shotType) {
        return Arrays.stream(values())
                .filter(v -> v.shotType.contains(shotType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("ShotScoreParser of failed. not matching score type : argument scoreType=%s", shotType)));
    }

    String parse(Integer score) {
        return scoreToStringFunction.apply(score)
                .replace("null", "");
    }
}
