package bowling;

public class State {

  public String symbol() {
    return "";
  }

  public static State of(int pins) {
    if (pins == 10) {
      return new Strike();
    }

    return new Pitching(pins);
  }

  public State roll(int second) {
    return null;
  }

}
