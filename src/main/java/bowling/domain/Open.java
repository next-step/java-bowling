package bowling.domain;

public class Open implements State {

  private Pins first;
  private Pins second;

  public Open(Pins first, int second) {
    this.first = first;
    this.second = Pins.roll(second);
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    if (isDoubleGutter()) {
      return String.join(DELIMITER, GUTTER, GUTTER);
    }

    if (first.isGutter()) {
      return String.join(DELIMITER, GUTTER, second.toString());
    }

    if (second.isGutter()) {
      return String.join(DELIMITER, first.toString(), GUTTER);
    }

    return String.join(DELIMITER, first.toString(), second.toString());
  }

  private boolean isDoubleGutter() {
    return second.isGutter() && first.isGutter();
  }

  @Override
  public String toString() {
    return "Open{" + "first=" + first + ", second=" + second + '}';
  }
}
