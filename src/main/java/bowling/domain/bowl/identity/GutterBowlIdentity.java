package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;
import bowling.domain.frame.Frame;

import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class GutterBowlIdentity extends AbstractBowlIdentity {

    public static final String GUTTER = "-|-";

    @Override
    public boolean identity(BowlResult bowlResult) {
        return bowlResult.getBowlCount() == SECOND_BOWL &&
                bowlResult.getTotalNumberOfPin() == MIN_NUMBER_OF_PIN;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public int getScore(Frame frame) {
        return MIN_NUMBER_OF_PIN;
    }

    @Override
    public String message(BowlResult bowlResult) {
        return GUTTER;
    }

}
