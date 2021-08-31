package bowling.state;

import bowling.pin.Pin;

public class Strike extends Finish{

  public static final int PIN_MAX = 10;

  private final Pin first;

  public Strike(final Pin first) {
    this.first = first;
  }

  @Override
  public String score() {
    return first.score();
  }

  @Override
  public int totalPin() {
    return PIN_MAX;
  }
}