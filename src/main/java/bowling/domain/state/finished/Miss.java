package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;

public class Miss implements State {

  FallenPins firstShot;
  FallenPins secondShot;

  @Override
  public boolean isFinished() {
    return true;
  }
}
