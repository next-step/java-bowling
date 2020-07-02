package bowling.model;

import java.util.Objects;

public class Score {

  int value;

  public Score(int value) {
    this.value = value;
  }

  public void add(int value) {
    this.value += value;
  }

  public void add(Score score) {
    this.value += score.value;
  }

  public int getValue() {
    return value;
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
    return value == score.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
