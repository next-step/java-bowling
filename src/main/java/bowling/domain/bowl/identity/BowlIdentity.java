package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;

public interface BowlIdentity {

    boolean identity(BowlResult bowlResult);
    boolean isCompleted();
    boolean isBonus();
    String format(BowlResult bowlResult);

}
