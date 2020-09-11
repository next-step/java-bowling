package bowling.domain;

public class Open implements State {

  static final String SECOND_GUTTER = "|-";
  static final String DOUBLE_GUTTER = "-|-";

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
      return DOUBLE_GUTTER;
    }

    if (first.isGutter()) {
      return FIRST_GUTTER + second;
    }

    return first + SECOND_GUTTER;
  }

  private boolean isDoubleGutter() {
    return second.isGutter() && first.isGutter();
  }

  @Override
  public String toString() {
    return "Open{" + "first=" + first + ", second=" + second + '}';
  }
}
