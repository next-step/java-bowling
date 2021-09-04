package bowling.frame;

import bowling.score.Score;
import bowling.state.State;
import bowling.state.StateFactory;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

  private static final int MAX_STATES_SIZE = 3;
  private static final int START_STATES_INDEX = 1;
  private static final String MSG_ERROR_END_FRAME = "이미 종료된 프레임입니다.";
  private static final String SEPARATOR = "|";
  private static final String SPARE = "/";
  private static final String STRIKE = "X";

  private final LinkedList<State> states = new LinkedList<>();

  public FinalFrame() {
    states.add(StateFactory.startPitch());
  }

  @Override
  public Frame play(final int pinCount) {
    validationEndFrame();
    State state = states.getLast();

    if (states.getLast().isFinish()) {
      validationLimitPitch(state);
      states.add(StateFactory.startPitch().nextPitch(pinCount));
      return this;
    }

    states.removeLast();
    states.add(state.nextPitch(pinCount));
    return this;
  }

  private void validationEndFrame() {
    if (states.size() == MAX_STATES_SIZE) {
      throw new RuntimeException(MSG_ERROR_END_FRAME);
    }
  }

  private void validationLimitPitch(final State state) {
    if (!state.scoreMessage().contains(SPARE) && !state.scoreMessage().contains(STRIKE)) {
      throw new RuntimeException(MSG_ERROR_END_FRAME);
    }
  }

  public boolean isGameEnd() {
    if (states.getFirst().isFinish()) {
      return isFinish();
    }
    return false;
  }

  private boolean isFinish() {
    try {
      return score().isFinishBallCount();
    } catch (RuntimeException e) {
      return false;
    }
  }

  @Override
  public String getScoreMessage() {
    return states.stream()
        .map(State::scoreMessage)
        .collect(Collectors.joining(SEPARATOR));
  }

  @Override
  public Score score() {
    Score score = states.getFirst().score();
    for (int i = START_STATES_INDEX; i < states.size(); i++) {
      score = states.get(i).calculateScore(score);
    }
    return score;
  }

  @Override
  public Score frameScoreAdd(Score score) {
    Score currentScore = score;
    for (State state : states) {
      score = state.calculateScore(currentScore);
    }

    return score;
  }
}