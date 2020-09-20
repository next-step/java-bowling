package bowling.domain.frame;

import bowling.domain.bowl.BowlResult;
import bowling.domain.bowl.FinalBowl;
import bowling.domain.bowl.FinalBowlResult;
import bowling.domain.score.Score;

import java.util.Iterator;

public class FinalFrame extends AbstractFrame {

    private final FinalBowl finalBowl = new FinalBowl();

    private FinalBowlResult finalBowlResult = new FinalBowlResult();

    public FinalFrame() {
        super(LAST_FRAME_NUMBER);
    }

    @Override
    public Frame bowl(int numberOfPin) {
        BowlResult bowlResult = finalBowl.bowl(numberOfPin);
        if (bowlResult.isBonus()) {
            finalBowl.add();
        }
        if (bowlResult.isFirst()) {
            finalBowlResult.add(bowlResult);
        }
        return this;
    }

    @Override
    public boolean isEnd() {
        return finalBowlResult.isEnd();
    }

    @Override
    public Iterator<Frame> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score getScore() {
        return finalBowlResult.getScore(this);
    }

    public boolean checkSpareBonus() {
        return finalBowlResult.checkSpareBonus();
    }

    public int getSpareBonus() {
        return finalBowlResult.getSpareBonus();
    }

    @Override
    public int getFirstNumberOfPin() {
        return finalBowlResult.getFirstNumberOfPin();
    }

    @Override
    public boolean isCompleted() {
        return isEnd();
    }

    @Override
    public int getTotalNumberOfPin() {
        return finalBowlResult.getTotalNumberOfPin();
    }

    @Override
    public boolean isNone() {
        return finalBowlResult.isNone();
    }

    @Override
    public boolean isStrike() {
        return finalBowlResult.isStrike();
    }

    @Override
    public String toString() {
        return finalBowlResult.format();
    }

}
