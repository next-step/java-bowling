package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

  private List<Pins> pins = new ArrayList<>();

  public FinalFrame() {
    super(10);
  }

  public FinalFrame roll(int pins) {
    if (this.pins.size() < 4) {
      if (state != null && !super.isDone()) {
        state = state.roll(pins);
      }

      if (state == null) {  // first
        state = State.of(pins);
      }

      if (!isDone()) {
        this.pins.add(Pins.roll(pins));
      }
    }

    return this;
  }

  public boolean isDone() {
    if (super.state instanceof Open) {
      return true;
    }
    return false;
  }

  public String symbol() {
    if (super.state instanceof Open) {
      return super.state.symbol();
    }

    if (super.state instanceof Spare) {
      return super.state.symbol() + "|" + new Bonus(pins.get(pins.size() - 1)).symbol();
    }

    return pins.stream()
        .map(p -> p.isStrike() ? "X" : p.isGutter() ? "-" : p.toString())
        .collect(Collectors.joining("|"));
  }

  @Override
  public String toString() {
    return "FinalFrame{" +
        "pins=" + pins +
        ", number=" + number +
        ", state=" + state +
        '}';
  }
}
