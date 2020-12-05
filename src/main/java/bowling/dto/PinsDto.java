package bowling.dto;

import java.util.List;

public class PinsDto {
    private final List<PinDto> rolls;

    public PinsDto(List<PinDto> rolls) {
        this.rolls = rolls;
    }

    public List<PinDto> getRolls() {
        return rolls;
    }
}
