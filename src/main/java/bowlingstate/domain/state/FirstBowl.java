package bowlingstate.domain.state;

import bowlingstate.domain.Score;
import bowlingstate.domain.States;
import java.util.List;

public class FirstBowl implements State {


  private static final String GUTTER_SYMBOL = "-";
  private static final int GUTTER_PIN_COUNT = 0;
  private final int countOfHitPin;

  FirstBowl(int countOfHitPin) {
    this.countOfHitPin = countOfHitPin;
  }

  @Override
  public State bowl(int countOfHitPin) {
    if (this.countOfHitPin + countOfHitPin == 10) {
      return new Spare(this.countOfHitPin, countOfHitPin);
    }
    return new Miss(this.countOfHitPin, countOfHitPin);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public List<String> state() {
    States states = new States();
    states.add(firstState());
    return states.states();
  }

  @Override
  public Score of() {
    throw new IllegalStateException("프레임이 진행중입니다.");
  }

  private String firstState() {
    if (countOfHitPin == GUTTER_PIN_COUNT) {
      return GUTTER_SYMBOL;
    }
    return String.valueOf(countOfHitPin);
  }
}
