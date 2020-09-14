package bowling.domain.state;

public class Pitching implements State {

  private Pins first;

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
      return GUTTER;
    }
    return first.toString();
  }

  @Override
  public String toString() {
    return "Pitching{" + "first=" + first + '}';
  }
}