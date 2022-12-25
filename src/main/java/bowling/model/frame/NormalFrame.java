package bowling.model.frame;


import bowling.model.Score;

import java.util.Optional;

public class NormalFrame extends AbstractFrame {

    public static final int LAST_FRAME_NUMBER = 9;
    public static final int FIRST_FRAME_NUMBER = 1;
    private Frame nextFrame;

    public NormalFrame(int number) {
        super(number);
    }

    public static NormalFrame first() {
        return new NormalFrame(FIRST_FRAME_NUMBER);
    }

    @Override
    public boolean isFinished() {
        return getCurrentState().isFinished();
    }

    @Override
    public Frame nextFrame() {
        if (isLastFrame()) {
            return nextFrame = new FinalFrame(getNumber() + 1);
        }
        return nextFrame = new NormalFrame(getNumber() + 1);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    private boolean isLastFrame() {
        return getNumber() == LAST_FRAME_NUMBER;
    }

    @Override
    public Score getScore() {
        Score score = getCurrentState().getScore();
        if (score.canCalculate()) {
            return score;
        }

        return nextFrame.addBonusScore(score);
    }

    @Override
    public Score addBonusScore(Score beforeScore) {
        Score score = getCurrentState().addBonusScore(beforeScore);
        if (score.canCalculate()) {
            return score;
        }

        return Optional.ofNullable(nextFrame)
                .map(nextFrame -> nextFrame.addBonusScore(score))
                .orElse(score);
    }
}
