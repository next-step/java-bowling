package bowling_step4.domain.frame;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;
import bowling_step4.domain.state.Ready;
import bowling_step4.domain.state.Spare;
import bowling_step4.domain.state.State;
import bowling_step4.domain.state.Strike;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FinalFrame implements Frame {

  private static final int MIN_PITCH_COUNT = 2;
  private static final int MAX_PITCH_COUNT = 3;
  private static final int FINAL_FRAME_PLAY_COUNT = 9;
  public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";
  private final int playCount = FINAL_FRAME_PLAY_COUNT;
  private LinkedList<State> states;

  public FinalFrame() {
    this.states = new LinkedList<>();
    states.add(new Ready());
  }

  @Override
  public Frame play(Pin pinCount) {
    if (this.isEnd()) {
      throw new IllegalArgumentException(INVALID_END_PLAY);
    }

    if (states.getLast().isFinish()) {
      states.add(new Ready());
    }

    State state = states.getLast();
    states.removeLast();
    states.addLast(state.play(pinCount));

    return this;

  }


  public boolean isEnd() {
    if (states.isEmpty()) {
      return false;
    }

    if (!hasBonusPitch() && this.sumAllPitchCount() == MIN_PITCH_COUNT) {
      return true;
    }

    return this.sumAllPitchCount() == MAX_PITCH_COUNT;
  }

  private boolean hasBonusPitch() {
    return states.stream()
        .anyMatch(state -> isStrikeOrSpare(state));
  }

  private boolean isStrikeOrSpare(State state) {
    if (state instanceof Strike || state instanceof Spare) {
      return true;
    }

    return false;
  }

  private int sumAllPitchCount() {
    return states.stream()
        .mapToInt(State::getPitchCount)
        .sum();
  }

  @Override
  public Frame next() {
    throw new IllegalArgumentException(INVALID_END_PLAY);
  }

  @Override
  public Score getScore() {

    if (!isEnd()) {
      return Score.ofUndefind();
    }

    Score score = getFirstScore();
    for (int i = 1; i < states.size(); i++) {
      State state = states.get(i);
      score = state.calculateAdditionalScore(score);
    }

    return score;
  }

  private Score getFirstScore() {
    return states.getFirst().getScore();
  }


  public List<State> getStates() {
    return Collections.unmodifiableList(states);
  }

  @Override
  public int getPlayCount() {
    return playCount;
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {
    Score score = beforeScore;
    for (State state : states) {
      score = state.calculateAdditionalScore(score);
      if (score.isEndCalculate()) {
        return score;
      }
    }
    return score;

  }

}
