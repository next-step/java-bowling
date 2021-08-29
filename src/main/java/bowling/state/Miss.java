package bowling.state;

import bowling.pin.Pin;

public class Miss extends Finish{

  private final Pin first;

  private final Pin second;

  public Miss(final Pin first, final Pin second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String score() {
    return second.score(first);
  }
}
