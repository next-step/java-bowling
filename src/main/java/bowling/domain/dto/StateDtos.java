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
        if (Objects.isNull(states) || states.size() == 0) {
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_OR_EMPTY);
        }
    }

    public static StateDtos of(final List<State> states) {
        return new StateDtos(states);
    }

    public String getSymbol() {
        return this.stateDtos.stream()
                .filter(stateDto -> !stateDto.isReady())
                .map(StateConverter::convertToSymbol)
                .collect(Collectors.joining(StateExpression.DELIMITER));
    }
}
