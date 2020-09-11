package bowling.domain;

public interface State {

  String FIRST_GUTTER = "-|";

  static State of(int pins) {
    Pins pitching = Pins.roll(pins);
    if (pitching.isStrike()) {
      return new Strike(pitching);
    }

    return new Pitching(pitching);
  }

  State roll(int second);

  String symbol();

}