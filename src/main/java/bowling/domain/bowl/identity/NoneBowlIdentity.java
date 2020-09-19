package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;

public class NoneBowlIdentity extends AbstractBowlIdentity {

    public static final String NONE = "";

    @Override
    public boolean identity(BowlResult bowlResult) {
        return bowlResult.isNone();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public String message(BowlResult bowlResult) {
        return NONE;
    }

}
