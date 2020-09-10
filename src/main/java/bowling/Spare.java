package bowling;

public class Spare extends Pitching implements State {

  private Pins second;

  public Spare(Pins first, int second) {
    super(first);
    this.second = Pins.roll(second);
  }

  @Override
  public String symbol() {
    return super.symbol() + "|/";
  }
}
