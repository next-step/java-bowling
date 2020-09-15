package bowling.domain.state;

public interface State {

  String DELIMITER = "|";
  String STRIKE = "X";
  String SPARE = "/";
  String GUTTER = "-";

  static State of(int pins) {
    Pins pitching = Pins.roll(pins);
    if (pitching.isStrike()) {
      return new Strike(pitching);
    }

    return new Pitching(pitching);
  }

  State roll(int second);

  String symbol();

  default boolean isDone() {
    return false;
  }

}