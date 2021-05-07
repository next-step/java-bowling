package bowling.domain;

import java.util.Objects;

public class Pins {

  public static final int MIN = 0;
  public static final int MAX = 10;

  private final int count;

  public Pins(int count) {
    if (count < MIN) {
      throw new IllegalArgumentException("핀의 갯수는 0보다 커야한다.");
    }
    if (count > MAX) {
      throw new IllegalArgumentException("핀의 갯수는 10을 넘을 수 없다.");
    }
    this.count = count;
  }

  public Pins() {
    this(10);
  }

  public Pins hit(int hitCount) {
    return new Pins(count - hitCount);
  }

  public int count() {
    return count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pins pins = (Pins) o;
    return count == pins.count;
  }

  @Override
  public int hashCode() {
    return Objects.hash(count);
  }
}
