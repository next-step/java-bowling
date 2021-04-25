package bowling.domain;

import java.util.Arrays;

public enum ScoreSymbol {
  STRIKE(10, "X"),
  SPARE(10, "/"),
  GUTTER(0, "-"),
  MISS(-1, "");

  private final int pin;
  private final String mark;

  ScoreSymbol(int pin, String mark) {
    this.pin = pin;
    this.mark = mark;
  }

  public static ScoreSymbol symbol(int pin, boolean isFirst) {
    ScoreSymbol scoreSymbol = Arrays.stream(values())
        .filter(bowl -> bowl.pin == pin)
        .findFirst()
        .orElse(MISS);

    if (!isFirst && scoreSymbol == STRIKE) {
      return ScoreSymbol.SPARE;
    }

    return scoreSymbol;
  }

  public String mark() {
    return mark;
  }
}
