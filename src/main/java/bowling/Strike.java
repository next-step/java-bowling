package bowling;

public class Strike implements State {

  @Override
  public State roll(Pins second) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String symbol() {
    return "X";
  }

}
