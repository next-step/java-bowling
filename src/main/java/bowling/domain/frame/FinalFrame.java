package bowling.domain.frame;

import bowling.domain.bowl.BowlResult;
import bowling.domain.bowl.FinalBowl;
import bowling.domain.bowl.FinalBowlResult;
import bowling.domain.score.Score;

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
    public Score getScore() {
        return finalBowlResult.getScore(this);
    }

    @Override
    public boolean isCompleted() {
        return isEnd();
    }

    public int getTotalNumberOfPin() {
        return finalBowlResult.getTotalNumberOfPin();
    }

    public boolean isStrike() {
        return finalBowlResult.isStrike();
    }

    @Override
    public boolean isEnd() {
        return finalBowlResult.isEnd();
    }

    @Override
    public String toString() {
        return finalBowlResult.format();
    }

    public boolean checkSpareBonus() {
        return finalBowlResult.checkSpareBonus();
    }

    public int getSpareBonus() {
        return finalBowlResult.getSpareBonus();
    }

    public boolean checkStrikeBonus() {
        return finalBowlResult.checkStrikeBonus();
    }

    public int getStrikeBonus() {
        return finalBowlResult.getStrikeBonus();
    }

}
