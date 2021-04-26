package bowling.dto;

import java.util.function.Function;

public enum StateDto {

    STRIKE(ignored -> "X"),
    SPARE(rollResultsDto -> rollResultsDto.getFirstRoll() + "|/"),
    MISS(rollResultsDto -> rollResultsDto.getFirstRoll() + "|" + rollResultsDto.getSecondRoll()),
    PLAYING(RollResultsDto::getFirstRoll),
    READY(ignored -> ""),
    ;


    private final Function<RollResultsDto, String> converter;

    StateDto(Function<RollResultsDto, String> converter) {
        this.converter = converter;
    }

    public String export(RollResultsDto rollResultsDto) {
        return converter.apply(rollResultsDto);
    }
}
