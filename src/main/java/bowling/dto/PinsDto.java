package bowling.dto;

import java.util.List;

public class PinsDto {
    private final List<PinDto> pins;

    public PinsDto(List<PinDto> pins) {
        this.pins = pins;
    }

    public List<PinDto> getPins() {
        return pins;
    }
}
