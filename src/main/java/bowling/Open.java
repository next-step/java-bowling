package bowling;

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
    if (second.isGutter()) {
      return first.isGutter() ? "-|-" : first + "|-";
    }

    return first.isGutter() ? "-|" + second : first + "|" + second;
  }

  @Override
  public String toString() {
    return "Open{" +
        "second=" + second +
        '}';
  }
}
