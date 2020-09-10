package bowling;

public class Spare  extends Pitching {

  private int second;

  public Spare(int first, int second) {
    super(first);
    this.second = second;
  }

  public String symbol() {
    return super.symbol() + "|/";
  }
}
