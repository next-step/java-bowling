package bowling.domain.engine.frame.state;

import bowling.domain.engine.roll.RollResult;

public interface State {

    boolean isFinished();

    State transit(RollResult rollResult);

    String export();

}
