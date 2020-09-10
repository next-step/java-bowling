package bowling;

public class Strike implements State {

  private Pins first;

  public Strike(Pins first) {
    this.first = first;
  }

  @Override
  public State roll(int second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    return "X";
  }

}
