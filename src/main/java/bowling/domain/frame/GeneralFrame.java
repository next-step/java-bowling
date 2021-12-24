package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.end.ResultState;
import bowling.domain.state.end.Results;
import bowling.domain.state.end.first.NextAbleState;
import java.util.List;

public class GeneralFrame extends Frame {

    private static final int MAX_HIT_PIN_COUNT = 10;

    public GeneralFrame() {
        super();
    }

    public GeneralFrame(Progress progress, List<ResultState> resultStates) {
        this(progress, new Results(resultStates));
    }

    public GeneralFrame(Progress progress, Results results) {
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
    protected Progress nextProgress(ResultState resultState) {
        if (isNextAbleState(resultState)) {
            return ProgressFactory.progress(resultState);
        }

        return ProgressFactory.closed();
    }

    @Override
    protected boolean isNextAbleState(ResultState resultState) {
        return (results.size() < GENERAL_ROUND_NUMBER) && (resultState instanceof NextAbleState);
    }

}