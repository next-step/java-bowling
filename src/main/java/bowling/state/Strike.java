package bowling.state;

import bowling.pin.Pin;
import bowling.score.Score;

public class Strike extends Finish {

  private static final String STRIKE = "X";

  private final Pin first;

  public Strike(final Pin first) {
    this.first = first;
  }

  @Override
  public String scoreMessage() {
    return STRIKE;
  }

  @Override
  public Pin totalPin() {
    return first;
  }

  @Override
  public Score score() {
    return Score.strike();
  }
}