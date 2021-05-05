package bowling.domain.state.finished;

import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

public class Spare implements State {
  private final FallenPins firstShot;
  private final FallenPins secondShot;

  private Spare(FallenPins firstShot, FallenPins secondShot){
    this.firstShot = firstShot;
    this.secondShot = secondShot;
  }

  public static Spare of(FallenPins firstShot, FallenPins secondShot){
    return new Spare(firstShot, secondShot);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public State bowl(FallenPins pins) {
    throw new CannotThrowBallException();
  }

  @Override
  public String show() {
    return null;
  }
}
