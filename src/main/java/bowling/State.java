package bowling;

public interface State {

  static State of(int pins) {
    Pins pitching = Pins.roll(pins);
    if (pitching.isStrike()) {
      return new Strike(pitching);
    }

    return new Pitching(pitching);
  }

  String symbol();

  State roll(int second);

}