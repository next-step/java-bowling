package bowling.domain.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Final implements State {

  private boolean canTriple = true;
  private List<State> states = new ArrayList<>();

  @Override
  public State roll(int pins) {
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
  public String symbol() {
    return states.stream()
        .map(State::symbol)
        .collect(Collectors.joining(State.DELIMITER));
  }

  @Override
  public boolean isDone() {
    if (this.states.size() == 3) {
      return true;
    }

    if (impossibleTriple()) {
      return true;
    }

    return false;
  }

  private boolean impossibleTriple() {
    return this.states.size() == 2 && !canTriple;
  }

  @Override
  public String toString() {
    return "Final{" +
        "canTriple=" + canTriple +
        ", states=" + states +
        '}';
  }
}

