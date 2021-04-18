package bowling.domain;

import java.util.Arrays;

public enum Score {
  STRIKE(10, "X"),
  SPARE(10, "/"),
  GUTTER(0, "-"),
  MISS(-1, "");

  private final int pin;
  private final String mark;

  Score(int pin, String mark) {
    this.pin = pin;
    this.mark = mark;
  }

  public static Score score(int pin, boolean isFirst) {
    Score score = Arrays.stream(values())
        .filter(bowl -> bowl.pin == pin)
        .findFirst()
        .orElse(MISS);

    if (!isFirst && score == STRIKE) {
      return Score.SPARE;
    }

    return score;
  }
}
