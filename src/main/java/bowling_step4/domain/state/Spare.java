package bowling_step4.domain.state;

import bowling_step4.domain.Pin;
import bowling_step4.domain.Score;

public class Spare implements State {

  private static final String INVALID_END_PLAY = "더이상 진행할 수 없습니다.";
  private static final String INVALID_PIN_COUNT = "잘못된 투구입니다.";
  private final Pin firstPin;
  private final Pin secondPin;

  public Spare(int firstPin, int secondPin) {
    this(new Pin(firstPin), new Pin(secondPin));
  }

  public Spare(Pin firstPin, Pin secondPin) {
    validate(firstPin, secondPin);
    this.firstPin = firstPin;
    this.secondPin = secondPin;
  }

  private void validate(Pin firstPin, Pin secondPin) {
    if (!firstPin.validate(secondPin) || !firstPin.isSpare(secondPin)) {
      throw new IllegalArgumentException(INVALID_PIN_COUNT);
    }
  }

  @Override
  public int getPitchCount() {
    return 2;
  }

  @Override
  public Score getScore() {
    return Score.ofSpare();
  }

  @Override
  public Score calculateAdditionalScore(Score beforeScore) {
    beforeScore = firstPin.sumScore(beforeScore);
    if (beforeScore.isEndCalculate()) {
      return beforeScore;
    }
    beforeScore = secondPin.sumScore(beforeScore);
    return beforeScore;
  }

  @Override
  public int getTotalCount() {
    return 10;
  }

  @Override
  public String toString() {
    if (firstPin.isGutter()) {
      return "-|/";
    }

    return firstPin.toString() + "|/";
  }

  @Override
  public State play(Pin fallenPin) {
    throw new IllegalArgumentException(INVALID_END_PLAY);
  }

  @Override
  public boolean isFinish() {
    return true;
  }

}
