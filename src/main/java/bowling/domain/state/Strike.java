package bowling.domain.state;

import bowling.exception.EndedFrameException;

public class Strike implements EndedState {
    @Override
    public ThrowingState bowl() {
        throw new EndedFrameException();
    }

    @Override
    public String symbol() {
        return "X";
    }
}
