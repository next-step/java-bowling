package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;

import static bowling.domain.NumberOfPin.MAX_NUMBER_OF_PIN;

public class StrikeBowlIdentity extends AbstractBowlIdentity {

    public static final String STRIKE = "X";

    @Override
    public boolean identity(BowlResult bowlResult) {
        return bowlResult.getBowlCount() == FIRST_BOWL &&
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
    public String message(BowlResult bowlResult) {
        return STRIKE;
    }

}
