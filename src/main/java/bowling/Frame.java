package bowling;

import java.util.Objects;

public class Frame {

  private int number;
  private State state;

  public Frame(int number) {
    this.number = number;
  }

  public Frame roll(int pins) {
    if (state == null) {
      state = State.of(pins);

      return this;
    }

    if (isDone()) {
      Frame next = new Frame(number + 1);
      next.roll(pins);
      return next;
    }

    state = state.roll(pins);
    return this;
  }

  public boolean isDone() {
    if (state instanceof Pitching) {
      return false;
    }
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Frame frame = (Frame) o;
    return number == frame.number;
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  @Override
  public String toString() {
    return "Frame{" + "state=" + state + '}';
  }
}
