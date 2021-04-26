package bowling.dto;

import bowling.domain.engine.roll.RollResult;

public class RollResultDto {

    private final String result;

    public RollResultDto(String result) {
        this.result = result;
    }

    public static RollResultDto of(RollResult rollResult) {
        if (rollResult.getValue() == 0) {
            return new RollResultDto("-");
        }
        return new RollResultDto(String.valueOf(rollResult.getValue()));
    }

    public static RollResultDto empty() {
        return new RollResultDto("");
    }

    public String getResult() {
        return result;
    }

}
