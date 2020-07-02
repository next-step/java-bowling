package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Result;
import bowling.domain.score.Score;

public class NormalFrame implements Frame {

    private final int index;
    private final FrameScore frameScore;
    private Frame nextFrame;

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
        this.nextFrame = isNextFinal ? FinalFrame.create(nextIndex) : new NormalFrame(nextIndex);
        return this.nextFrame;
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
    public Score calculateTotalScore() {
        Result result = frameScore.checkResult();
        return result.calculateTotalScore(frameScore.calculateTotalScore(), nextFrame.getFrameScore());
    }

    @Override
    public int getIndex() {
        return index;
    }
}
