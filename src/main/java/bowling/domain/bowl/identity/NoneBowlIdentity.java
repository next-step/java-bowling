package bowling.domain.bowl.identity;

import bowling.domain.bowl.BowlResult;
import bowling.domain.frame.Frame;
import bowling.domain.score.NormalScore;
import bowling.domain.score.Score;

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
    public Score getScore(Frame frame) {
        return new NormalScore(frame);
    }

    @Override
    public String message(BowlResult bowlResult) {
        return NONE;
    }

}
