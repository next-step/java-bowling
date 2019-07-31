package bowling.state;

import bowling.Pins;
import bowling.Score;

public class Miss extends DoubleBowl {

  public Miss(Pins firstPins, Pins secondPins) {
    super(firstPins, secondPins);
  }

  @Override
  public String desc() {
    return getFirstPins().desc() + RESULT_CONCAT_SYMBOL + getSecondPins().desc();
  }

  @Override
  public Score getScore() {
    return new Score(getFirstPins().count() + getSecondPins().count(), 0);
  }

}
