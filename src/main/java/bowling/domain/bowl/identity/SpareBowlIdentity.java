package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

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
    public int getScore(Frame frame) {
        int bonusScore = ((NormalFrame) frame).getNextFrameFirstNumberOfPin();
        if (bonusScore == -1) {
            return -1;
        }
        return MAX_NUMBER_OF_PIN + bonusScore;
    }

    @Override
    public String message(BowlResult bowlResult) {
        return MessageFormat.format(SPARE, bowlResult.getFirstNumberOfPin());
    }

}
