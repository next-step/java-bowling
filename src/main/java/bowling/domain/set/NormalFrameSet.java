package bowling.domain.set;

import bowling.domain.History;
import bowling.domain.score.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NormalFrameSet implements FrameSet {

    public static final int START_SET_PLAY_COUNT = 1;
    public static final int END_SET_PLAY_COUNT = 9;

    private final BaseFrameSet frameSet;

    private NormalFrameSet(int playCount, State state) {
        assertPlayCount(playCount);
        this.frameSet = BaseFrameSet.create(playCount, state);
    }

    public static FrameSet createFirst() {
        return create(START_SET_PLAY_COUNT);
    }

    public static FrameSet create(int playCount) {
        return new NormalFrameSet(playCount, new Ready());
    }

    @Override
    public void play(int hitCount) {
        frameSet.play(hitCount);
    }

    @Override
    public FrameSet getNext() {
        FrameSet nextFrameSet = createNextFrame(getPlayCount());
        frameSet.setNextFrameSet(nextFrameSet);

        return nextFrameSet;
    }

    private FrameSet createNextFrame(int playCount) {
        if (playCount == NormalFrameSet.END_SET_PLAY_COUNT) {
            return LastFrameSet.create();
        }

        return create(playCount + 1);
    }

    @Override
    public boolean isEndedFrame() {
        return frameSet.isEndedFrame();
    }

    @Override
    public boolean isEndedGame() {
        return false;
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
        return frameSet.getPlayCount();
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

    private void assertPlayCount(int playCount) {
        if (playCount > END_SET_PLAY_COUNT) {
            throw new IllegalArgumentException("일반 세트는 더이상 진행할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameSet that = (NormalFrameSet) o;
        return Objects.equals(frameSet, that.frameSet);
    }

    @Override
    public int hashCode() {
        return frameSet.hashCode();
    }
}
