package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.state.finished.Strike;
import bowling.domain.turn.FallenPins;

public class Ready implements State {
  private static final String EMPTY = "";

  @Override
  public boolean isFinished() {
    return false;
  }

  private Ready(){

  }

  public static Ready of(){
    return new Ready();
  }

  @Override
  public State bowl(FallenPins pins) {
    if(pins.isStrike()){
      return Strike.of(pins);
    }

    return FirstShot.of(pins);
  }

  @Override
  public String show() {
    return EMPTY;
  }
}
