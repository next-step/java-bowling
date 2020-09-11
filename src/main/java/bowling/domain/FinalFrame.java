package bowling.domain;

import bowling.domain.state.Bonus;
import bowling.domain.state.Open;
import bowling.domain.state.Pins;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

  private static final int MAX_ROLL = 3;
  private List<Pins> pins = new ArrayList<>();

  public FinalFrame() {
    super(10);
  }

  @Override
  public FinalFrame roll(int pins) {
    if (this.pins.size() < MAX_ROLL) {
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

  @Override
  public boolean isDone() {
    if (super.state instanceof Open) {
      return true;
    }

    if (this.pins.size() == MAX_ROLL) {
      return true;
    }

    return false;
  }

  @Override
  public String symbol() {
    if (super.state instanceof Open) {
      return super.state.symbol();
    }

    if (super.state instanceof Spare) {
      return super.state.symbol() + State.DELIMITER + new Bonus(pins.get(pins.size() - 1)).symbol();
    }

    if (super.state instanceof Strike) {
      return super.state.symbol() + State.DELIMITER + new Bonus(pins.get(pins.size() - 1)).symbol();
    }

    return pins.stream()
        .map(p -> p.isStrike() ? State.STRIKE : p.isGutter() ? State.GUTTER : p.toString())
        .collect(Collectors.joining(State.DELIMITER));
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
