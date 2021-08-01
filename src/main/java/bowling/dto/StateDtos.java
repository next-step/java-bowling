package bowling.dto;

import bowling.domain.state.State;

import java.util.List;
import java.util.stream.Collectors;

public class StateDtos {
    private final List<StateDto> stateDtos;

    public StateDtos(List<State> states) {
        stateDtos = states.stream()
                .map(StateDto::from)
                .collect(Collectors.toList());
    }

    public static StateDtos from(List<State> states) {
        return new StateDtos(states);
    }

    public List<StateDto> getStateDtos() {
        return stateDtos;
    }
}
