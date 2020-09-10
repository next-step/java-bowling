package bowling;

public class Spare implements State {

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
    return first.isGutter() ? "-|/" : first + "|/";
  }

  @Override
  public String toString() {
    return "Spare{" + "first=" + first + ", second=" + second + '}';
  }
}
