package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.state.State;
import java.util.Optional;

public class FinalFrame extends Frame {

    @Override
    public State bowl(Pin pin) {
        return null;
    }

    @Override
    public boolean hasBonusRound() {
        return true;
        // TODO:  2021/12/23 (kiyeon_kim1)
    }

    @Override
    public Optional<Frame> next() {
        return Optional.empty();
    }
}
