package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

public class Strike implements State {

  private Strike(){

  }

  public static State of() {
    return new Strike();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public State bowl(FallenPins pins) {
    throw new CannotThrowBallException();
  }
}
