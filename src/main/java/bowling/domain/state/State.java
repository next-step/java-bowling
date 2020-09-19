package bowling.domain.state;

import bowling.domain.Score;

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

  int pins();

  default boolean isDone() {
    return false;
  }

  Score score(Score score);

  Score accumulate(Score score);
}