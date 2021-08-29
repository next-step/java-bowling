package bowling.state;

import bowling.pin.Pin;

public class Spare extends Finish{

  private final Pin first;

  private final Pin second;

  public Spare(final Pin first, final Pin second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String Score() {
    return second.score(first);
  }
}
