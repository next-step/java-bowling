package bowling.dto;

import java.util.List;

public class RollsDto {
    private final List<RollDto> rolls;

    public RollsDto(List<RollDto> rolls) {
        this.rolls = rolls;
    }

    public List<RollDto> getRolls() {
        return rolls;
    }
}
