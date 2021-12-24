package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.progress.Progress;
import bowling.domain.progress.Opened;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.first.NextAbleState;
import java.util.Optional;

public class GeneralFrame extends Frame {

    private final Frame next;

    public GeneralFrame(Frame next) {
        this.next = next;
    }

    @Override
    public Progress bowl(Pin pin) {
        if (!(progress instanceof Opened)) {
            throw new BowlingProgressException("게임을 더 진행할 수 없는 상태입니다.");
        }

        return searchNextProgress(pin);
    }

    @Override
    protected Progress nextProgress(EndState endState) {
        if (endState instanceof NextAbleState) {
            return ProgressFactory.progress(endState);
        }

        return ProgressFactory.closed();
    }


    @Override
    public Optional<Frame> next() {
        return Optional.ofNullable(next);
    }
}