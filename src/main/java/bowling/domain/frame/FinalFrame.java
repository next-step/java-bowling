package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.progress.Opened;
import bowling.domain.state.end.BonusAbleState;
import bowling.domain.state.end.EndState;
import bowling.domain.progress.Progress;
import bowling.domain.state.end.first.NextAbleState;
import java.util.Optional;

public class FinalFrame extends Frame {

    private static final int GENERAL_ROUND_NUMBER = 2;

    @Override
    public Progress bowl(Pin pin) {
        if (!(this.progress instanceof Opened)) {
            throw new BowlingProgressException("게임을 더 진행할 수 없는 상태입니다.");
        }

        return searchNextProgress(pin);
    }

    @Override
    protected Progress nextProgress(EndState endState) {
        if (this.results.size() > GENERAL_ROUND_NUMBER) {
            return ProgressFactory.closed();
        }

        if (nextBowlAble(endState)) {
            return ProgressFactory.progress(endState);
        }

        return ProgressFactory.closed();
    }

    private boolean nextBowlAble(EndState endState) {
        return containBonusAbleState() || isNextAbleState(endState);
    }

    private boolean isNextAbleState(EndState endState) {
        return this.results.size() < GENERAL_ROUND_NUMBER &&
            endState.isInstanceOf(NextAbleState.class);
    }

    private boolean containBonusAbleState() {
        return this.results.stream()
            .anyMatch(result -> result instanceof BonusAbleState);
    }

    @Override
    public Optional<Frame> next() {
        return Optional.empty();
    }
}
