package bowling.domain.set;

import bowling.domain.History;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;
import java.util.Objects;

public class LastFrameSet implements FrameSet {

    public static final int PLAY_COUNT = 10;

    private final BaseFrameSet frameSet;

    private LastFrameSet(State state) {
        this.frameSet = BaseFrameSet.create(LastFrameSet.PLAY_COUNT, state);
    }

    public static FrameSet create() {
        return new LastFrameSet(new Ready());
    }

    @Override
    public void play(int hitCount) {
        frameSet.play(hitCount);
    }

    @Override
    public FrameSet getNext() {
        if (isEndedFrame() && getState().isBonusPlayableState()) {
            FrameSet bonusSet = BonusFrameSet.create();
            frameSet.setNextFrameSet(bonusSet);

            return bonusSet;
        }

        return null;
    }

    @Override
    public boolean isEndedFrame() {
        return frameSet.isEndedFrame();
    }

    @Override
    public boolean isEndedGame() {
        return getState().isEnd() && !getState().isBonusPlayableState();
    }

    @Override
    public State getState() {
        return frameSet.getState();
    }

    @Override
    public int getPlayCount() {
        return LastFrameSet.PLAY_COUNT;
    }

    @Override
    public int getTotalScore() {
        return frameSet.getTotalScore();
    }

    @Override
    public History getHistory() {
        return frameSet.getHistory();
    }

    @Override
    public int calculateAdditionalScore(Score score) {
        return frameSet.calculateAdditionalScore(score);
    }

    @Override
    public boolean canCalculateScore() {
        return frameSet.canCalculateScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrameSet that = (LastFrameSet) o;
        return Objects.equals(frameSet, that.frameSet);
    }

    @Override
    public int hashCode() {
        return frameSet.hashCode();
    }
}
