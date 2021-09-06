package bowling.state;

import bowling.pin.Pin;
import bowling.score.Score;

public class NextPitch extends Running {

  private static final int MAX_PINS = 10;
  private static final String MSG_ERROR_CAN_NOT_CALCULATE = "점수를 합산 할 수 없습니다.";

  private final Pin firstPin;

  private Pin totalPin;

  public NextPitch(final Pin firstPin) {
    this.firstPin = firstPin;
  }

  @Override
  public State nextPitch(final int fallenPin) {
    Pin currentPin = Pin.from(fallenPin);
    totalPin = currentPin.totalDownPin(firstPin);

    if (isSpare()) {
      return new Spare(firstPin, currentPin);
    }
    return new Miss(firstPin, currentPin);
  }

  private boolean isSpare() {
    return totalPin.equals(Pin.from(MAX_PINS));
  }

  @Override
  public String scoreMessage() {
    return firstPin.toString();
  }

  @Override
  public Pin totalPin() {
    return totalPin;
  }

  @Override
  public Score calculateScore(Score score) {
    score = firstPin.sumScore(score);

    if (score.isFinishBallCount()) {
      return score;
    }
    throw new RuntimeException(MSG_ERROR_CAN_NOT_CALCULATE);
  }
}