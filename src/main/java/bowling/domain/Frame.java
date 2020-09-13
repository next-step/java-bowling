package bowling.domain;

import bowling.domain.state.State;
import java.util.Objects;

public class Frame implements Comparable<Frame> {

  private static final int MAX_FRAME = 10;

  protected int number;
  protected State state;

  public Frame(int number) {
    this.number = number;
  }

  public Frame roll(int pins) {
    if (state == null) {  // first
      state = State.of(pins);

      return this;
    }

    if (nextFinalFrame()) {
      FinalFrame finalFrame = new FinalFrame();
      finalFrame.roll(pins);

      return finalFrame;
    }

    if (isDone()) {
      Frame next = new Frame(number + 1);
      next.roll(pins);
      return next;
    }

    state = state.roll(pins);
    return this;
  }

  private boolean nextFinalFrame() {
    return isDone() && number + 1 == MAX_FRAME;
  }

  public boolean isDone() {
    return state.isDone();
  }

  public String symbol() {
    return state.symbol();
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
    return "Frame{" + "number=" + number + ", state=" + state + '}';
  }

  @Override
  public int compareTo(Frame that) {
    return Integer.compare(this.number, that.number);
  }
}
