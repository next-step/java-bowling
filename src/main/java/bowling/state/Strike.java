package bowling.state;

import bowling.pin.Pin;

public class Strike extends Finish {

  private static final String STRIKE = "X";

  private final Pin first;

  public Strike(final Pin first) {
    this.first = first;
  }

  @Override
  public String score() {
    return STRIKE;
  }

  @Override
  public int totalPin() {
    return first.totalDownPin();
  }
}