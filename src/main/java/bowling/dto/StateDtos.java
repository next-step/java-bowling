package bowling.dto;

import java.util.List;

public class StateDtos {
    private final List<StateDto> stateDtos;

    private StateDtos(final List<StateDto> stateDtos) {
        this.stateDtos = stateDtos;
    }

    public static StateDtos from(final List<StateDto> frames) {
        return new StateDtos(frames);
    }

    public List<StateDto> getStates() {
        return stateDtos;
    }
}
