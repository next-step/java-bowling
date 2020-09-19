package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;
import bowling.domain.frame.Frame;

public interface BowlIdentity {

    boolean identity(BowlResult bowlResult);
    boolean isCompleted();
    boolean isBonus();
    int getScore(Frame frame);
    String format(BowlResult bowlResult);

}
