package bowling.domain;

import java.util.Objects;

public class Score {

  public static final int DECREASE_ZERO = 0;
  public static final int DECREASE_ONCE = 1;
  public static final int DECREASE_TWICE = 2;

  private final int acc;
  private final int decrease;

  private Score(int acc, int decrease) {
    this.acc = acc;
    this.decrease = decrease;
  }

  public static Score of(int acc) {
    return new Score(acc, DECREASE_ZERO);
  }

  public static Score strike(int acc) {
    return new Score(acc, DECREASE_TWICE);
  }

  public static Score spare(int acc) {
    return new Score(acc, DECREASE_ONCE);
  }

  public boolean isSpare() {
    return decrease == DECREASE_ONCE;
  }

  public boolean isStrike() {
    return decrease == DECREASE_TWICE;
  }

  public Score accumulate(int pins) {
    return new Score(acc + pins, DECREASE_ZERO);
  }

  public Score accumulateOnce(int pins) {
    return new Score(acc + pins, DECREASE_ONCE);
  }

  public Score accumulateTwice(int pins) {
    return new Score(acc + pins, DECREASE_TWICE);
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
