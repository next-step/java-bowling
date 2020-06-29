package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.FrameScore;

public class NormalFrame implements Frame {

    private final int index;
    private final FrameScore frameScore;

    private NormalFrame(int index) {
        this.index = index;
        this.frameScore = FrameScore.create();
    }

    public static NormalFrame createFirst() {
        return new NormalFrame(0);
    }

    @Override
    public Frame createNext(boolean isNextFinal) {
        int nextIndex = index + 1;
        return isNextFinal ? FinalFrame.create(nextIndex) : new NormalFrame(nextIndex);
    }

    @Override
    public boolean canAddMoreScore() {
        return !frameScore.getFirst().isPresent() || !frameScore.getSecond().isPresent();
    }

    @Override
    public void addScore(Score score) {
        frameScore.add(score);
    }

    @Override
    public FrameScore getFrameScore() {
        return frameScore;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
