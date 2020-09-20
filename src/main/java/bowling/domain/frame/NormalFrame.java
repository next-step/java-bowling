package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlResult;
import bowling.domain.score.Score;

public class NormalFrame extends AbstractFrame {

    private static final int FIRST_NORMAL_FRAME_NUMBER = 1;
    private static final int LAST_NORMAL_FRAME_NUMBER = 9;

    private final Bowl bowl = new Bowl();

    private BowlResult bowlResult = new BowlResult();

    public NormalFrame(int frameNumber) {
        super(frameNumber);
    }

    public static NormalFrame firstFrame() {
        return new NormalFrame(FIRST_NORMAL_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        bowlResult = bowl.bowl(numberOfPin);
        return isCompleted() ? createNextFrame() : this;
    }

    private Frame createNextFrame() {
        nextFrame = isLast() ?
                new FinalFrame() :
                new NormalFrame(frameNumber + 1);
        return nextFrame;
    }

    public boolean isLast() {
        return frameNumber == LAST_NORMAL_FRAME_NUMBER;
    }

    @Override
    public Score getScore() {
        return bowlResult.getScore(this);
    }

    @Override
    public int getTotalNumberOfPin() {
        return bowlResult.getTotalNumberOfPin();
    }

    @Override
    public boolean isStrike() {
        return bowlResult.isStrike();
    }

    @Override
    public boolean isCompleted() {
        return bowlResult.isCompleted();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String toString() {
        return bowlResult.format();
    }

    public boolean checkSpareBonus() {
        return bowlResult.checkSpareBonus();
    }

    public int getSpareBonus() {
        return bowlResult.getFirstNumberOfPin();
    }

    public boolean checkStrikeBonus() {
        return bowlResult.checkStrikeBonus();
    }

    public int getStrikeBonus() {
        return bowlResult.getTotalNumberOfPin();
    }

}
