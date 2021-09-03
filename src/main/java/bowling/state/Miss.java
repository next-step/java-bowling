package bowling.state;

import bowling.pin.Pin;

public class Miss extends Finish {

  private static final String SEPARATOR = "|";

  private final Pin first;

  private final Pin second;

  public Miss(final Pin first, final Pin second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String scoreMessage() {
    return first.toString() + SEPARATOR + second.toString();
  }

  @Override
  public Pin totalPin() {
    return second.totalDownPin(first);
  }
}
