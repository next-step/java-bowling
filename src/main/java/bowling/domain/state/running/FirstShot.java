package bowling.domain.state.running;

import bowling.domain.state.State;
import bowling.domain.state.finished.Miss;
import bowling.domain.state.finished.Spare;
import bowling.domain.turn.FallenPins;

public class FirstShot implements State {

  private final FallenPins firstShot;

  private FirstShot(FallenPins pins){
    this.firstShot = pins;
  }

  public static State of(FallenPins pins) {
    return new FirstShot(pins);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public State bowl(FallenPins pins) {
    if(firstShot.isSpare(pins)){
      return Spare.of(firstShot, pins);
    }

    return Miss.of(firstShot, pins);
  }
}
