package bowling.domain.state.finished;

import bowling.domain.Score;
import bowling.domain.turn.FallenPins;

public class Spare extends Finished {
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
