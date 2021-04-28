package bowlingstate.domain.state;

import bowlingstate.domain.Score;
import bowlingstate.domain.States;
import java.util.List;

public class Miss implements State {

  private static final String GUTTER_SYMBOL = "-";
  private static final int GUTTER_PIN_COUNT = 0;
  private final int firstPin;
  private final int secondPin;

  public Miss(int firstPin, int secondPin) {
    this.firstPin = firstPin;
    this.secondPin = secondPin;
  }

  @Override
  public State bowl(int countOfHitPin) {
    throw new IllegalStateException("프레임을 진행할 수 없습니다.");
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public List<String> state() {
    States states = new States();
    states.add(eachState(firstPin));
    states.add(eachState(secondPin));
    return states.states();
  }

  @Override
  public Score of() {
    return Score.ofMiss(firstPin, secondPin);
  }

  private String eachState(int countOfHitPin) {
    if (countOfHitPin == GUTTER_PIN_COUNT) {
      return GUTTER_SYMBOL;
    }
    return String.valueOf(countOfHitPin);
  }
}
