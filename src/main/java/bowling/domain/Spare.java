package bowling.domain;

public class Spare implements State {

  static final String SPARE = "|/";

  private Pins first;
  private Pins second;

  public Spare(Pins first, int second) {
    this.first = first;
    this.second = Pins.roll(second);
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    if (first.isGutter()) {
      return FIRST_GUTTER + SPARE;
    }

    return first + SPARE;
  }

  @Override
  public String toString() {
    return "Spare{" + "first=" + first + ", second=" + second + '}';
  }
}
