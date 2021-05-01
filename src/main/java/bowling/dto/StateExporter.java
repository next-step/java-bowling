package bowling.dto;

import java.util.function.Function;

public enum StateExporter {

    STRIKE(ignored -> "X"),
    SPARE(rollResultsDto -> makeDashIfZero(rollResultsDto.getFirstRoll()) + "|/"),
    MISS(rollResultsDto -> makeDashIfZero(rollResultsDto.getFirstRoll()) + "|" + makeDashIfZero(rollResultsDto.getSecondRoll())),
    PLAYING(RollResultsDto::getFirstRoll),
    READY(ignored -> ""),
    ;

    private static final String GUTTER_ORIGINAL_SCORE = "0";

    private final Function<RollResultsDto, String> converter;

    StateExporter(Function<RollResultsDto, String> converter) {
        this.converter = converter;
    }

    private static String makeDashIfZero(String result) {
        if (result.equals(GUTTER_ORIGINAL_SCORE)) {
            return "-";
        }

        return result;
    }

    public String export(RollResultsDto rollResultsDto) {
        return converter.apply(rollResultsDto);
    }
}
