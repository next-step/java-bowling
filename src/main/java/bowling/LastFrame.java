package bowling;

import bowling.state.Miss;
import bowling.state.Ready;
import bowling.state.State;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class LastFrame implements Frame {

  private static final int LAST_FRAME_NO = 10;
  private static final int LAST_FRAME_MAX_ROLL_COUNT = 3;

  private LinkedList<State> states = new LinkedList<>();
  private int bowlCount = 0;

  public LastFrame() {
    states.add(new Ready());
  }

  @Override
  public int frameNo() {
    return LAST_FRAME_NO;
  }

  @Override
  public String desc() {
    return states.stream()
        .map(State::desc)
        .collect(Collectors.joining("|"));
  }

  @Override
  public Frame bowl(Pins pins) {
    if (isGameEnd()) {
      throw new RuntimeException("게임오버");
    }
    bowlCount++;
    if (!states.getLast().isFinish()) {
      states.add(states.pop().bowl(pins));
      return this;
    }
    states.add(new Ready().bowl(pins));
    return this;
  }

  @Override
  public boolean isGameEnd() {
    return states.getLast() instanceof Miss || bowlCount == LAST_FRAME_MAX_ROLL_COUNT;
  }

  @Override
  public Score getScore() {
    if (!isGameEnd()) {
      return Score.noFinishScore();
    }
    return new Score(states.stream()
        .map(State::getScore)
        .map(Score::getScore)
        .reduce(0, (a, b) -> a + b), 0);
  }

  @Override
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
