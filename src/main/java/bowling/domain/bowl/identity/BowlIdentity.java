package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;
import bowling.domain.frame.Frame;
import bowling.domain.score.Score;

public interface BowlIdentity {

    boolean identity(BowlResult bowlResult);
    boolean isCompleted();
    boolean isBonus();
    Score getScore(Frame frame);
    String format(BowlResult bowlResult);

}
