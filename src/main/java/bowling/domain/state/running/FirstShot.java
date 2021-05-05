package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;

public class FirstShot implements State {

  private FallenPins firstShot;

  @Override
  public boolean isFinished() {
    return false;
  }
}
