package bowling.state;

import bowling.Pins;
import bowling.Score;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class LastFrameState {

  private LinkedList<State> states = new LinkedList<>();

  public LastFrameState() {
    this.states.add(new Ready());
  }

  public String desc() {
    return states.stream()
        .map(State::desc)
        .collect(Collectors.joining("|"));
  }

  public boolean isMiss() {
    return currentState() instanceof Miss;
  }

  private State currentState() {
    return states.getLast();
  }

  public boolean isFinished() {
    return currentState().isFinish();
  }

  public LastFrameState bowl(Pins pins) {
    if (!isFinished()) {
      states.add(states.pop().bowl(pins));
      return this;
    }
    states.add(new Ready().bowl(pins));
    return this;
  }

  public int getScore() {
    return states.stream()
        .map(State::getScore)
        .map(Score::getScore)
        .reduce(0, (a, b) -> a + b);
  }

  public Score addAdditionalScore(Score prevScore) {
    Score currentScore = prevScore;
    for (int i = 0; i < states.size(); i++) {
      Score score = states.get(i).addAdditionalScore(currentScore);
      if (score.isCompleteScore()) {
        return score;
      }
      currentScore = score;
    }
    return Score.noFinishScore();
  }
}
