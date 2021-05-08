package bowling.domain.state.finished;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;
import bowling.error.CannotThrowBallException;

public class Spare implements State {
  private final FallenPins firstShot;
  private final FallenPins secondShot;

  private Spare(FallenPins firstShot, FallenPins secondShot) {
    this.firstShot = firstShot;
    this.secondShot = secondShot;
  }

  public static Spare of(FallenPins firstShot, FallenPins secondShot) {
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
    return firstShot.show(secondShot);
  }

  @Override
  public Score calculateScore() {
    return Score.ofSpare();
  }

  @Override
  public Score addScore(Score score) {
    if (score.canCalculateScore()) {
      return score;
    }

    score = score.bowl(firstShot.pins());
    if (score.canCalculateScore()) {
      return score;
    }

    score = score.bowl(secondShot.pins());
    return score;
  }


}
