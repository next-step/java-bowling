package bowling.state;

import bowling.StateFactory;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalState implements State {

  private static final String ROLL_DELIMITER = "|";
  private static final int MAX_ROLL_COUNT = 3;

  LinkedList<State> states = new LinkedList<>();
  private int rollCount = 0;

  public FinalState() {
    this.states.add(new Ready());
  }

  @Override
  public State roll(int countOfPin) {

    if (isFinish()) {
      throw new RuntimeException("게임끝");
    }
    rollCount++;
    State currentState = states.getLast();

    if (currentState.isFinish()) {
      states.add(StateFactory.make(countOfPin));
      return this;
    }

    states.removeLast();
    states.add(currentState.roll(countOfPin));
    return this;
  }

  @Override
  public Boolean isFinish() {
    if (noPlay()) {
      return false;
    }
    return rollCount == MAX_ROLL_COUNT || states.getFirst() instanceof Miss;
  }

  private boolean noPlay() {
    return states.isEmpty();
  }

  @Override
  public String toString() {
    return states.stream()
        .map(State::toString)
        .collect(Collectors.joining(ROLL_DELIMITER));
  }

}
