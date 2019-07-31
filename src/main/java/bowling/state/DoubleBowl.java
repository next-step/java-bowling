package bowling.state;

import bowling.Pins;
import bowling.Score;

public abstract class DoubleBowl extends Finished {

  private Pins firstPins;
  private Pins secondPins;

  public DoubleBowl(Pins firstPins, Pins secondPins) {
    this.firstPins = firstPins;
    this.secondPins = secondPins;
  }

  public Pins getFirstPins() {
    return firstPins;
  }

  public Pins getSecondPins() {
    return secondPins;
  }

  @Override
  public Score addAdditionalScore(Score prevScore) {
    Score score = prevScore.addScore(new Score(firstPins.count(), 0));
    if (score.isCompleteScore()) {
      return score;
    }
    return score.addScore(new Score(secondPins.count(), 0));
  }

}
