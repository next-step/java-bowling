package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.state.State;
import bowling.domain.state.UpdateAbleState;
import java.util.Optional;

public class GeneralFrame extends Frame {

    private final Frame next;

    public GeneralFrame(Frame next) {
        this.next = next;
    }

    @Override
    public State bowl(Pin pin) {
        if (!(state instanceof UpdateAbleState)) {
            throw new BowlingProgressException("게임을 더 진행할 수 없는 상태입니다.");
        }

        return ((UpdateAbleState) state).getUpdateState(this, pin);
    }

    @Override
    public boolean hasBonusRound() {
        return false;
    }


    @Override
    public Optional<Frame> next() {
        return Optional.ofNullable(next);
    }
}
