package bowling.domain;

import java.util.Objects;

public class Score {

  private final int acc;
  private final int decrease;

  public Score(int acc) {
    this(acc, 0);
  }

  public Score(int acc, int decrease) {
    this.acc = acc;
    this.decrease = decrease;
  }

  public boolean isStrike() {
    return decrease == 2;
  }

  public boolean isSpare() {
    return decrease == 1;
  }

  public Score accumulate(int pins) {
    return new Score(acc + pins);
  }

  public Score accumulate(int pins, int decrease) {
    return new Score(acc + pins, decrease);
  }

  public boolean hasLeft() {
    return decrease > 0;
  }

  public int value() {
    return acc;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Score score = (Score) o;
    return acc == score.acc &&
        decrease == score.decrease;
  }

  @Override
  public int hashCode() {
    return Objects.hash(acc, decrease);
  }

  @Override
  public String toString() {
    return "Score{" +
        "acc=" + acc +
        ", decrease=" + decrease +
        '}';
  }
}
