package bowling.domain.state;

import bowling.domain.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Final implements State {

  private boolean canBonus = true;
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
        canBonus = states.get(0).score(null).hasLeft();
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
  public int pins() {
    return 0;
  }

  @Override
  public boolean isDone() {
    if (this.states.size() == 3) {
      return true;
    }

    if (impossibleTriple()) {
      return true;
    }

    if (!canBonus) {
      return true;
    }

    return false;
  }

  private boolean impossibleTriple() {
    return this.states.size() == 2 && !canTriple;
  }

  @Override
  public Score score(Score score) {
    if (isDone()) {
      return score.accumulate(states.stream()
          .map(state -> state.score(null))
          .mapToInt(s -> s.value())
          .sum());
    }

    return null;
  }

  @Override
  public Score accumulate(Score score) {
    Score acc = states.get(0).accumulate(score);

    if (acc == null) {
      return null;
    }

    if (acc.hasLeft()) {
      if (states.size() > 1) {
        return accumulate(acc);
      }
      return null;
    }

    return acc;
  }

  @Override
  public String toString() {
    return "Final{" +
        "canTriple=" + canTriple +
        ", states=" + states +
        '}';
  }
}

