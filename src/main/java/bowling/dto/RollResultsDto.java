package bowling.dto;

import bowling.domain.RollResult;

public class RollResultsDto {

    private final RollResultDto firstRoll;
    private final RollResultDto secondRoll;

    public RollResultsDto(RollResultDto firstRoll, RollResultDto secondRoll) {
        this.firstRoll = firstRoll;
        this.secondRoll = secondRoll;
    }

    public static RollResultsDto of(RollResult firstRoll, RollResult secondRoll) {
        return new RollResultsDto(RollResultDto.of(firstRoll), RollResultDto.of(secondRoll));
    }

    public static RollResultsDto of(RollResult firstRoll) {
        return new RollResultsDto(RollResultDto.of(firstRoll), RollResultDto.empty());
    }

    public static RollResultsDto empty() {
        return new RollResultsDto(RollResultDto.empty(), RollResultDto.empty());
    }

    public String getFirstRoll() {
        return firstRoll.getResult();
    }

    public String getSecondRoll() {
        return secondRoll.getResult();
    }
}
