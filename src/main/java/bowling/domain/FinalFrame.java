package bowling.domain;

import bowling.domain.state.Open;
import bowling.domain.state.Pitching;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

  private static final int MAX_ROLL = 3;

  private List<State> states = new ArrayList<>();

  public FinalFrame() {
    super(10);
  }

  @Override
  public FinalFrame roll(int pins) {
    if (!states.isEmpty()) {
      State last = states.get(states.size() - 1);
      if (last instanceof Pitching) {
        states.remove(states.size() - 1);
        states.add(last.roll(pins));
        return this;
      }
    }

    states.add(State.of(pins));
    state = states.get(0);
    return this;
  }

  @Override
  public boolean isDone() {
    if (this.states.size() == MAX_ROLL) {
      return true;
    }

    State last = states.get(states.size() - 1);
    if (state instanceof Strike && (last instanceof Spare || last instanceof Open)) {
      return true;
    }

    if (state instanceof Spare && this.states.size() == 2) {
      return true;
    }

    return false;
  }

  @Override
  public String symbol() {
    return states.stream()
        .map(State::symbol)
        .collect(Collectors.joining(State.DELIMITER));
  }

  @Override
  public String toString() {
    return "FinalFrame{" +
        "states=" + states +
        ", number=" + number +
        ", state=" + state +
        '}';
  }
}
