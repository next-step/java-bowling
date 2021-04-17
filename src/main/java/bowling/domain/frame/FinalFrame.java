package bowling.domain.frame;

import bowling.domain.PinCount;
import bowling.domain.state.Ready;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import java.util.LinkedList;

public class FinalFrame implements Frame {

  private static final int FINAL_PLAY_COUNT = 10;
  private static final int FINAL_FRAME_COUNT = 3;

  private LinkedList<State> states = new LinkedList<>();

  public FinalFrame() {
    states.add(new Ready());
  }


  @Override
  public void play(PinCount pinCount) {
    validate();

    State currentState = states.getLast();

    if (currentState.isEnd()) {
      State state = new Ready().play(pinCount);
      states.add(state);
      return;
    }

    if (currentState instanceof Ready) {
      states.removeLast();
    }

    State newState = currentState.play(pinCount);
    states.add(newState);

  }

  private void validate() {
    if (isEnd()) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getPlayCount() {
    return 10;
  }

  @Override
  public State getState() {
    return states.getLast();
  }

  private boolean isStrikeOrSpare(State state) {
    return state instanceof Strike || state instanceof Spare;
  }

  private boolean hasBonus() {
    return states.stream()
        .anyMatch(this::isStrikeOrSpare);
  }

  @Override
  public boolean isEnd() {
    if (hasBonus()) {
      return states.size() == 3;
    }

    return states.size() == 2;
  }

  @Override
  public Frame nextFrame() {
    return this;
  }

}