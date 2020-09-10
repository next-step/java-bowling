package bowling;

public interface State {

  static State of(int pins) {
    if (pins == 10) {
      return new Strike();
    }

    return new Pitching(pins);
  }

  String symbol();

  State roll(int second);

}
