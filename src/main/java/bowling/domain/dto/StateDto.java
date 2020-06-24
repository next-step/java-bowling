package bowling.domain.dto;

import bowling.domain.state.State;
import bowling.domain.state.running.Ready;
import bowling.exception.message.ErrorMessage;

import java.util.Objects;

public class StateDto {

    private final State state;

    private StateDto(final State state) {
        validate(state);
        this.state = state;
    }

    public static StateDto of(final State state) {
        return new StateDto(state);
    }

    private void validate(final State state) {
        if (Objects.isNull(state)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_VALUE);
        }
    }

    public Class<? extends State> getStateClassType() {
        return this.state.getClass();
    }

    public boolean isReady() {
        return getStateClassType().equals(Ready.class);
    }

    public String getFirstPins() {
        return this.state.getFirstPins().getHitCount();
    }

    public String getSecondPins() {
        return this.state.getSecondPins().getHitCount();
    }
}
