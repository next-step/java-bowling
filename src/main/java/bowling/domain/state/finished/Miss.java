package bowling.domain.state.finished;

import bowling.domain.Score;
import bowling.domain.state.State;
import bowling.domain.turn.FallenPins;

public class Miss extends Finished {

  private final FallenPins firstShot;
  private final FallenPins secondShot;

  private Miss(FallenPins firstShot, FallenPins secondShot) {
    this.firstShot = firstShot;
    this.secondShot = secondShot;
  }

  public static State of(FallenPins firstShot, FallenPins secondShot) {
    firstShot.checkAddable(secondShot);
    return new Miss(firstShot, secondShot);
  }

  @Override
  public String show() {
    return firstShot.show(secondShot);
  }

  @Override
  public Score calculateScore() {
    return Score.ofMiss(firstShot.addShot(secondShot));
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
