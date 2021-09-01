package bowling.state;

import bowling.pin.Pin;

public class Miss extends Finish{

  private static final String SEPARATOR = "|";

  private final Pin first;

  private final Pin second;

  public Miss(final Pin first, final Pin second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String score() {
    return first.totalDownPin() + SEPARATOR + second.totalDownPin();
  }

  @Override
  public int totalPin() {
    return second.totalDownPin(first);
  }
}
