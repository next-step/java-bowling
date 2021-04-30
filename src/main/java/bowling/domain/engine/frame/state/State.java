package bowling.domain.engine.frame.state;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.Score;

public interface State {

    boolean isFinished();

    State transit(RollResult rollResult);

    String export();

    default boolean canPromoteToBonusState() {
        return false;
    }

    Score addScoreTo(Score score);

    Score createScore();

}
