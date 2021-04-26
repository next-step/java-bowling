package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public class FirstBowl implements State {

  private final Pin firstPin;

  public FirstBowl(int firstPin) {
    this(new Pin(firstPin));
  }

  public FirstBowl(Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State play(Pin fallenPin) {
    if (fallenPin.isGutter()) {
      return new SecondGutter(firstPin);
    }

    if (firstPin.isSpare(fallenPin)) {
      return new Spare(firstPin, fallenPin);
    }

    return new Miss(firstPin, fallenPin);
  }

  @Override
  public int getPitchCount() {
    return 1;
  }

  @Override
  public int getTotalCount() {
    return firstPin.getCount();
  }

  @Override
  public String toString() {
    return firstPin.toString();
  }

  @Override
  public boolean isFinish() {
    return false;
  }

  @Override
  public Score getScore() {
    return Score.ofUndefind();
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {
    beforeScore = firstPin.sumScore(beforeScore);
    if (beforeScore.isEndCalculate()) {
      return beforeScore;
    }
    return Score.ofUndefind();
  }
}
