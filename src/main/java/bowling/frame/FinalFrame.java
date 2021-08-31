package bowling.frame;

import bowling.state.State;
import bowling.state.StateFactory;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

  private static final int MAX_STATES_SIZE = 2;
  private static final int MAX_FRAME_SIZE = 10;
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
  public Frame play(final int pinCount, final int size) {

    validationEndFrame(size);

    State state = states.getLast();

    if (state.isFinish()) {

      validationLimitPitch(state);

      states.add(StateFactory.startPitch().nextPitch(pinCount));
      stop = true;

      return this;
    }

    states.removeLast();
    states.add(state.nextPitch(pinCount));
    limitBallCount++;

    checkLimitBallCount();
    return this;
  }

  private void checkLimitBallCount() {
    if (states.getLast().totalPin() != MAX_FRAME_SIZE && limitBallCount == MAX_STATES_SIZE) {
      stop = true;
    }
  }

  private void validationEndFrame(final int size) {
    if (states.size() > MAX_STATES_SIZE && size == MAX_FRAME_SIZE) {
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