package bowling.frame;

import bowling.state.State;
import bowling.state.StateFactory;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

  private static final int MAX_LIMIT_BALL_COUNT = 2;
  private static final int MAX_STATES_SIZE = 3;
  private static final int MAX_PIN = 10;
  private static final String MSG_ERROR_END_FRAME = "이미 종료된 프레임입니다.";
  private static final String SEPARATOR = "|";
  private static final String SPARE = "/";
  private static final String STRIKE = "X";

  private boolean stop = false;

  private int limitBallCount;

  private final LinkedList<State> states = new LinkedList<>();

  public FinalFrame() {
    states.add(StateFactory.startPitch());
  }

  @Override
  public Frame play(final int pinCount) {

    validationEndFrame();

    State state = states.getLast();

    if (state.isFinish()) {
      validationLimitPitch(state);
      states.add(StateFactory.startPitch().nextPitch(pinCount));

      checkMaxStateSize();
      return this;
    }

    states.removeLast();
    states.add(state.nextPitch(pinCount));
    limitBallCount++;

    checkLimitBallCount();
    return this;
  }

  private void checkMaxStateSize() {
    if (states.size() == MAX_STATES_SIZE || limitBallCount == MAX_LIMIT_BALL_COUNT) {
      stop = true;
    }
  }

  private void checkLimitBallCount() {
    checkLimitTwoPitches();
    checkLimitThreePitches();
  }

  private void checkLimitThreePitches() {
    if (states.getLast().totalPin() == MAX_PIN && states.size() == MAX_LIMIT_BALL_COUNT) {
      stop = true;
    }
  }

  private void checkLimitTwoPitches() {
    if (states.getLast().totalPin() != MAX_PIN && limitBallCount == MAX_LIMIT_BALL_COUNT) {
      stop = true;
    }
  }

  private void validationEndFrame() {
    if (states.size() == MAX_STATES_SIZE) {
      throw new RuntimeException(MSG_ERROR_END_FRAME);
    }
  }

  private void validationLimitPitch(final State state) {
    if (!state.score().contains(SPARE) && !state.score().contains(STRIKE)) {
      throw new RuntimeException(MSG_ERROR_END_FRAME);
    }
  }

  @Override
  public String getScore() {
    return states.stream()
        .map(State::score)
        .collect(Collectors.joining(SEPARATOR));
  }

  public boolean isGameEnd() {
    return stop;
  }
}