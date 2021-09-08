package bowling.state;

import bowling.exception.ScoreCalculateException;
import bowling.pin.Pin;
import bowling.score.Score;

public class FirstPitch extends Running {

  private static final int MAX_PINS = 10;
  private static final String MSG_ERROR_CAN_NOT_CALCULATE = "점수를 합산 할 수 없습니다.";

  private Pin currentPin;

  public FirstPitch() {
  }

  @Override
  public State nextPitch(final int fallenPin) {
    currentPin = Pin.from(fallenPin);

    if (isStrike()) {
      return new Strike(currentPin);
    }
    return new NextPitch(currentPin);
  }

  private boolean isStrike() {
    return currentPin.equals(Pin.from(MAX_PINS));
  }

  @Override
  public String scoreMessage() {
    return "";
  }

  @Override
  public Pin totalPin() {
    return currentPin;
  }

  @Override
  public Score calculateScore(final Score score) {
    throw new ScoreCalculateException(MSG_ERROR_CAN_NOT_CALCULATE);
  }
}