package bowling;

public class Spare extends Pitching implements State {

  private Pins second;

  public Spare(Pins first, Pins second) {
    super(first);
    this.second = second;
  }

  @Override
  public String symbol() {
    return super.symbol() + "|/";
  }
}
