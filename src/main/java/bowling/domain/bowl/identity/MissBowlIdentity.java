package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;

import java.text.MessageFormat;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;
import static bowling.domain.NumberOfPin.MIN_NUMBER_OF_PIN;

public class MissBowlIdentity extends AbstractBowlIdentity {

    public static final String MISS = "{0}|{1}";

    @Override
    public boolean identity(BowlResult bowlResult) {
        return bowlResult.getBowlCount() == SECOND_BOWL &&
                (bowlResult.getTotalNumberOfPin() > MIN_NUMBER_OF_PIN && bowlResult.getTotalNumberOfPin() < MAX_NUMBER_OF_PIN);
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
    public String message(BowlResult bowlResult) {
        return MessageFormat.format(MISS, bowlResult.getFirstNumberOfPins(), bowlResult.getSecondNumberOfPins());
    }

}
