package bowling;

public class Open extends Pitching {
  private int second;

  public Open(int first, int second) {
    super(first);
    this.second = second;
  }

  public String symbol() {
    if (second == 0) {
      return super.symbol() + "|-";
    }
    return super.symbol() + "|" + second;
  }
}
