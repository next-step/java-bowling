package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.first.NextAbleState;
import java.util.List;
import java.util.Optional;

public class GeneralFrame extends Frame {

    private static final int MAX_HIT_PIN_COUNT = 10;

    private Frame next = null;

    public GeneralFrame(Frame next) {
        super();
        this.next = next;
    }

    public GeneralFrame(Progress progress, List<EndState> results) {
        super(progress, results);

        valid();
    }

    private void valid() {
        if (sumHitPinsCount() > MAX_HIT_PIN_COUNT) {
            throw new IllegalArgumentException("각 프레임별 HitPin 갯수는 10개를 넘길 수 없어요.");
        }
    }

    @Override
    public Frame bowl(Pin pin) {
        if (isClosed()) {
            throw new BowlingProgressException("게임을 더 진행할 수 없는 상태입니다.");
        }

        return new GeneralFrame(searchNextProgress(pin), this.results);
    }

    @Override
    protected Progress nextProgress(EndState endState) {
        if (isNextAbleState(endState)) {
            return ProgressFactory.progress(endState);
        }

        return ProgressFactory.closed();
    }

    @Override
    protected boolean isNextAbleState(EndState endState) {
        return (results.size() < GENERAL_ROUND_NUMBER) && (endState instanceof NextAbleState);
    }


    @Override
    public Optional<Frame> next() {
        return Optional.ofNullable(next);
    }
}