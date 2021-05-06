package bowling.domain.state.finished;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

public class Miss implements State {

  FallenPins firstShot;
  FallenPins secondShot;

  private Miss(FallenPins firstShot, FallenPins secondShot) {
    this.firstShot = firstShot;
    this.secondShot = secondShot;
  }

  public static State of(FallenPins firstShot, FallenPins secondShot) {
    firstShot.checkAddable(secondShot);
    return new Miss(firstShot, secondShot);
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
    return firstShot.show(secondShot);
  }

  @Override
  public Score calculateScore() {
    return Score.ofMiss(firstShot.addShot(secondShot));
  }
}
