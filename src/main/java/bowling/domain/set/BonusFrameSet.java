package bowling.domain.set;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.List;
import java.util.Objects;

public class BonusFrameSet implements FrameSet {

    public static final int PLAY_COUNT = 11;

    private final FrameSet frameSet;

    private BonusFrameSet(State state) {
        this.frameSet = BaseFrameSet.create(BonusFrameSet.PLAY_COUNT, state);
    }

    public static BonusFrameSet create() {
        return new BonusFrameSet(new Ready());
    }

    @Override
    public void play(int hitCount) {
        frameSet.play(hitCount);
    }

    @Override
    public FrameSet next() {
        return this;
    }

    @Override
    public boolean isEndedFrame() {
        return true;
    }

    @Override
    public boolean isEndedGame() {
        return !(getState() instanceof Ready);
    }

    @Override
    public FrameSet snapShot() {
        return frameSet.snapShot();
    }

    @Override
    public State getState() {
        return frameSet.getState();
    }

    @Override
    public int getPlayCount() {
        return BonusFrameSet.PLAY_COUNT;
    }

    @Override
    public int getScore() {
        return frameSet.getScore();
    }

    @Override
    public List<State> getHistory() {
        return frameSet.getHistory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusFrameSet that = (BonusFrameSet) o;
        return Objects.equals(frameSet, that.frameSet);
    }

    @Override
    public int hashCode() {
        return frameSet.hashCode();
    }
}
