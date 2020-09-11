package bowling.domain.state;

import static bowling.domain.state.Strike.STRIKE;

public class Bonus implements State {

  private Pins pins;

  public Bonus(Pins pins) {
    this.pins = pins;
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    return pins.isStrike() ? STRIKE : pins.isGutter() ? GUTTER : pins.toString();
  }

  @Override
  public String toString() {
    return "Bonus{" + "pins=" + pins + '}';
  }
}
