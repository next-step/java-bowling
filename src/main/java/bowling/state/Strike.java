package bowling.state;

import bowling.pin.Pin;

public class Strike extends Finish{

  private final Pin first;

  public Strike(final Pin first) {
    this.first = first;
  }

  @Override
  public String Score() {
    return first.score();
  }
}
