package bowling.domain.dto;

import bowling.domain.state.State;
import bowling.domain.state.StateExpression;
import bowling.exception.message.ErrorMessage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StateDtos {

    private final List<StateDto> stateDtos;

    private StateDtos(final List<State> states) {
        validate(states);
        this.stateDtos = states.stream()
                .map(StateDto::of)
                .collect(Collectors.toList());
    }

    private void validate(final List<State> states) {
        if (Objects.isNull(states)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_VALUE);
        }
    }

    public static StateDtos of(final List<State> states) {
        return new StateDtos(states);
    }

    public String getDesc() {
        return this.stateDtos.stream()
                .filter(stateDto -> !stateDto.isReady())
                .map(StateConverter::convertToDesc)
                .collect(Collectors.joining(StateExpression.DELIMITER));
    }
}
