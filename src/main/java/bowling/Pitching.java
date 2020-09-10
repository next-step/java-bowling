package bowling;

public class Pitching implements State {

  private Pins first;

  public Pitching(int first) {
    this(Pins.roll(first));
  }

  public Pitching(Pins first) {
    this.first = first;
  }

  @Override
  public State roll(int second) {
    if (first.isSpare(second)) {
      return new Spare(first, second);
    }
    return new Open(first, second);
  }

  @Override
  public String symbol() {
    if (first.isGutter()) {
      return "-";
    }
    return first.toString();
  }

}