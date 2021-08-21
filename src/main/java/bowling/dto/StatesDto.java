package bowling.dto;

import java.util.List;

public class StatesDto {
    private final List<StateDto> stateDtos;

    private StatesDto(final List<StateDto> stateDtos) {
        this.stateDtos = stateDtos;
    }

    public static StatesDto from(final List<StateDto> frames) {
        return new StatesDto(frames);
    }

    public List<StateDto> getStates() {
        return stateDtos;
    }
}
