package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Result;
import bowling.domain.score.Score;

import java.util.Optional;

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
    public Optional<Score> calculateTotalScore() {
        if (!frameScore.canCheckResult()) {
            return Optional.empty();
        }

        Result result = frameScore.checkResult();
        FrameScore nextFrameScore = nextFrame.getFrameScore();

        Score totalScore = result.calculateTotalScore(frameScore.calculateTotalScore(), nextFrameScore);
        return Optional.of(totalScore);
    }

    @Override
    public int getIndex() {
        return index;
    }
}
