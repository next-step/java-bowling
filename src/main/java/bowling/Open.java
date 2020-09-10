package bowling;

public class Open extends Pitching implements State {

  private Pins second;

  public Open(Pins first, Pins second) {
    super(first);
    this.second = second;
  }

  public String symbol() {
    if (second.isGutter()) {
      return super.symbol() + "|-";
    }
    return super.symbol() + "|" + second;
  }
}
