package dto;

import score.ScoreInfo;
import score.Status;

import java.util.Arrays;

public enum FrontConverter {
    STRIKE("|", (status, score) -> "X"),
    SPARE("|", (status, score) -> "/"),
    MISS("|", (status, score) -> String.valueOf(score)),
    GUTTER("|", (status, score) -> String.valueOf(score)),
    NONE("", (status, score) -> "");

    public static final String EMPTY = "";
    private final String delimiter;
    private final ConvertStrategy convertStrategy;

    FrontConverter(String delimiter, ConvertStrategy convertStrategy) {
        this.delimiter = delimiter;
        this.convertStrategy = convertStrategy;
    }

    public static String convert(ScoreInfo scoreInfo) {
        if (scoreInfo == null) {
            return EMPTY;
        }
        FrontConverter converter = findByStatus(scoreInfo.getStatus());
        return converter.delimiter + converter.convertStrategy.change(scoreInfo.getStatus(), scoreInfo.getScore());
    }

    private static FrontConverter findByStatus(Status status) {
        return Arrays.stream(values())
                .filter(converter -> isEqualName(status, converter))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean isEqualName(Status status, FrontConverter converter) {
        return converter.name().equals(status.name());
    }
}
