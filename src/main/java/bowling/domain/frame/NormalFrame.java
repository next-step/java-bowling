package bowling.domain.frame;

import bowling.domain.frame.status.Start;
import bowling.domain.frame.status.Status;
import bowling.domain.score.Score;

public class NormalFrame extends Frame {

    private Status status;
    private Frame nextFrame;

    public NormalFrame(int frameNumber) {
        super(frameNumber);
        status = new Start();
    }

    @Override
    public Frame record(int downedPin) {
        if (isEnd()) {
            nextFrame = createNextFrame();
            nextFrame.record(downedPin);
            return nextFrame;
        }

        status = status.record(downedPin);

        return this;
    }

    @Override
    public boolean isEnd() {
        return status.isEnd();
    }

    @Override
    public String getDescriptionForm() {
        return status.getDescription();
    }

    @Override
    protected Score addBonus(Score originalScore) {
        if (originalScore.isFixed()) {
            return originalScore;
        }

        Score bonusAddedScore = status.addBonus(originalScore);

        if (nextFrame != null && !bonusAddedScore.isFixed()) {
            return nextFrame.addBonus(bonusAddedScore);
        }

        return bonusAddedScore;
    }

    @Override
    public int calculateScore() {
        Score originalScore = status.calculateBaseScoreOfFrame();

        if (originalScore.isFixed()) {
            return originalScore.calculateScore();
        }

        if (nextFrame == null) {
            return Score.UNDEFINED;
        }

        Score bonusAddedScore = nextFrame.addBonus(originalScore);

        if (bonusAddedScore.isFixed()) {
            return bonusAddedScore.calculateScore();
        }

        return Score.UNDEFINED;
    }
}
