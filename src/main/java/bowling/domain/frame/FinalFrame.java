package bowling.domain.frame;

import bowling.Pin;
import bowling.domain.progress.Progress;
import bowling.domain.progress.ProgressFactory;
import bowling.domain.state.end.BonusAbleState;
import bowling.domain.state.end.EndState;
import bowling.domain.state.end.Strike;
import bowling.domain.state.end.first.NextAbleState;
import java.util.List;
import java.util.Optional;

public class FinalFrame extends Frame {

    private static final int MAX_OF_GENERAL_HIT_PIN_COUNT = 10;

    public FinalFrame() {

    }

    public FinalFrame(Progress progress, List<EndState> results) {
        super(progress, results);

        if (isGeneralRound()) {
            valid();
        }
    }

    private void valid() {
        if (sumHitPinsCount(result -> !result.isInstanceOf(Strike.class))
            > MAX_OF_GENERAL_HIT_PIN_COUNT) {
            throw new IllegalArgumentException("일반 게임의 HitPin 갯수는 10개를 넘길 수 없어요.");
        }
    }

    @Override
    public Frame bowl(Pin pin) {
        if (isClosed()) {
            throw new BowlingProgressException("게임을 더 진행할 수 없는 상태입니다.");
        }

        return new FinalFrame(searchNextProgress(pin), this.results);
    }

    @Override
    protected Progress nextProgress(EndState endState) {
        if (isGeneralRound() && nextBowlAble(endState)) {
            return ProgressFactory.progress(endState);
        }

        return ProgressFactory.closed();
    }

    private boolean nextBowlAble(EndState endState) {
        return isNextAbleState(endState) || containBonusAbleState();
    }

    @Override
    protected boolean isNextAbleState(EndState endState) {
        return isGeneralRoundBefore() && endState.isInstanceOf(NextAbleState.class);
    }

    private boolean isGeneralRoundBefore() {
        return this.results.size() < GENERAL_ROUND_NUMBER;
    }

    private boolean isGeneralRound() {
        return this.results.size() <= GENERAL_ROUND_NUMBER;
    }

    private boolean containBonusAbleState() {
        return this.results.stream()
            .anyMatch(result -> result instanceof BonusAbleState);
    }
}
