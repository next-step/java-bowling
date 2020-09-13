package bowling.domain;

import bowling.domain.state.State;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame extends Frame {

  private boolean canTriple = true;
  private List<State> states = new ArrayList<>();

  public FinalFrame() {
    super(10);
  }

  @Override
  public FinalFrame roll(int pins) {
    if (!states.isEmpty()) {
      State last = states.get(states.size() - 1);
      if (!last.isDone()) {
        states.remove(states.size() - 1);
        states.add(last.roll(pins));

        canTriple = false;
        return this;
      }
    }

    states.add(State.of(pins));
    return this;
  }

  @Override
  public boolean isDone() {
    if (this.states.size() == 3) {
      return true;
    }

    if (atLeastDouble()) {
      return false;
    }

    if (atLeastOneStrikeOrOneSpare()) {
      return true;
    }

    return false;
  }

  private boolean atLeastDouble() {
    return this.states.size() == 2 && canTriple;
  }

  private boolean atLeastOneStrikeOrOneSpare() {
    return this.states.size() == 2 && !canTriple;
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
        ", canTriple=" + canTriple +
        '}';
  }
}
