package bowling.dto;

import java.util.function.Function;

public enum StateExporter {

    STRIKE(ignored -> "X"),
    SPARE(rollResultsDto -> rollResultsDto.getFirstRoll() + "|/"),
    MISS(rollResultsDto -> rollResultsDto.getFirstRoll() + "|" + rollResultsDto.getSecondRoll()),
    PLAYING(RollResultsDto::getFirstRoll),
    READY(ignored -> ""),
    ;


    private final Function<RollResultsDto, String> converter;

    StateExporter(Function<RollResultsDto, String> converter) {
        this.converter = converter;
    }

    public String export(RollResultsDto rollResultsDto) {
        return converter.apply(rollResultsDto);
    }
}
