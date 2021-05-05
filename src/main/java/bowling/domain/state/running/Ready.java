package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.state.finished.Strike;
import bowling.domain.turn.FallenPins;

public class Ready implements State {

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public State bowl(FallenPins pins) {
    if(pins.isStrike()){
      return Strike.of();
    }

    return FirstShot.of(pins);
  }
}
