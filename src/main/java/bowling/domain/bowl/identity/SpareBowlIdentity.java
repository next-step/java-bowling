package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.LastSpareNormalScore;
import bowling.domain.score.Score;
import bowling.domain.score.SpareNormalScore;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class SpareBowlIdentity extends AbstractBowlIdentity {

    public static final String SPARE = "{0}|/";

    @Override
    public boolean identity(BowlResult bowlResult) {
        return bowlResult.getBowlCount() == SECOND_BOWL &&
                bowlResult.getTotalNumberOfPin() == MAX_NUMBER_OF_PIN;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public boolean isBonus() {
        return true;
    }

    @Override
    public boolean isStrike() {
        return false;
    }

    @Override
    public Score getScore(NormalFrame normalFrame) {
        return normalFrame.isLast() ?
                new LastSpareNormalScore(normalFrame) :
                new SpareNormalScore(normalFrame);
    }

    @Override
    public String message(BowlResult bowlResult) {
        return MessageFormat.format(SPARE, bowlResult.getFirstNumberOfPin());
    }

}
