package dto;

import score.ScoreInfo;
import score.Status;

import java.util.Arrays;

public enum FrontConverter {
    STRIKE("", (status, score) -> "X"),
    SPARE("|", (status, score) -> "/"),
    MISS("|", (status, score) -> String.valueOf(score)),
    GUTTER("|", (status, score) -> String.valueOf(score)),
    NONE("", (status, score) -> "");

    private final String delimiter;
    private final ConvertStrategy convertStrategy;

    FrontConverter(String delimiter, ConvertStrategy convertStrategy) {
        this.delimiter = delimiter;
        this.convertStrategy = convertStrategy;
    }

    public static String convert(ScoreInfo scoreInfo) {
        FrontConverter converter = findByStatus(scoreInfo.getStatus());
        String converted = converter.delimiter + converter.convertStrategy.change(scoreInfo.getStatus(), scoreInfo.getScore());

        if (converted.startsWith("|")) {
            return converted.substring(1);
        }

        return converted;
    }

    private static FrontConverter findByStatus(Status status) {
        return Arrays.stream(values())
                .filter(converter -> isEqualName(status, converter))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean isEqualName(Status status, FrontConverter converter) {
        if (status == null) {
            return false;
        }
        return converter.name().equals(status.name());
    }
}
