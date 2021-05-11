package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

public abstract class Finished implements State {

  @Override
  public State bowl(FallenPins pins) {
    throw new CannotThrowBallException();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

}
