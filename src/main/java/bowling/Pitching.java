package bowling;

public class Pitching implements State {

  private int first;

  public Pitching(int first) {
    this.first = first;
  }

  public State roll(int second) {
    if (first + second == 10) {
      return new Spare(first, second);
    }
    return new Open(first, second);
  }

  public String symbol() {
    if (first == 0) {
      return "-";
    }
    return String.valueOf(first);
  }

}