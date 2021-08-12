package bowling.domain.state.pitching;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.BunchState;
import bowling.domain.state.CommonState;
import bowling.domain.state.result.Finish;

import java.util.List;

public class LastPitching extends CommonState {
    private static final int LIMIT_ATTEMPT_COUNT = 3;

    private final BunchState bunchState;
    private int attemptCount;

    protected LastPitching() {
        bunchState = BunchState.of();
    }

    public static LastPitching of() {
        return new LastPitching();
    }

    @Override
    public CommonState hitPins(Pins pins) {
        attemptCount += 1;
        bunchState.hitPins(pins);

        return updateState();
    }

    @Override
    public boolean isFinish() {
        return attemptCount == LIMIT_ATTEMPT_COUNT || bunchState.isFinish();
    }

    @Override
    public List<Integer> getHitPins() {
        return bunchState.getHitPins();
    }

    @Override
    public List<CommonState> getState() {
        return bunchState.getState();
    }

    @Override
    public Score score() {
        return bunchState.score();
    }

    @Override
    public Score addBonusScore(Score score) {
        return bunchState.addScore(score);
    }

    private CommonState updateState() {
        if (isFinish()) {
            return Finish.of(bunchState);
        }

        bunchState.addExtraChance();
        return this;
    }
}
