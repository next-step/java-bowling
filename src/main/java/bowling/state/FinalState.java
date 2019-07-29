package bowling.state;

import bowling.Score;
import bowling.StateFactory;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalState implements State {

  private static final String ROLL_DELIMITER = "|";
  private static final int MAX_ROLL_COUNT = 3;

  private LinkedList<State> states = new LinkedList<>();
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

  @Override
  public Score score() {
    if (isFinish()) {
      return sumScore();
    }
    return Score.defaultScore();
  }

  private Score sumScore() {
    int totalSum = states.stream()
        .map(State::score)
        .map(Score::scoreValue)
        .reduce(0, (a, b) -> a + b);
    return new Score(totalSum, 0);
  }

  @Override
  public Score addScore(Score previousScore) {
    Score currentScore = previousScore;
    for (int i = 0; i < states.size(); i++) {
      Score score = states.get(i).addScore(currentScore);
      if (score.hasNoAdditionalScore()) {
        return score;
      }
      currentScore = score;
    }
    return Score.defaultScore();
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FinalState that = (FinalState) o;
    return rollCount == that.rollCount &&
        Objects.equals(states, that.states);
  }

  @Override
  public int hashCode() {
    return Objects.hash(states, rollCount);
  }
}
