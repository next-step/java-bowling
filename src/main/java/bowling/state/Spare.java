package bowling.state;

import bowling.Pins;
import bowling.Score;

public class Spare extends DoubleBowl {

  private static final String SPARE_SYMBOL = "/";

  public Spare(Pins firstPins, Pins secondPins) {
    super(firstPins, secondPins);
  }

  @Override
  public String desc() {
    return getFirstPins().desc() + RESULT_CONCAT_SYMBOL + SPARE_SYMBOL;
  }

  @Override
  public Score getScore() {
    return Score.spare();
  }

}
