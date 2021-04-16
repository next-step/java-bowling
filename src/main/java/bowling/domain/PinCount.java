package bowling.domain;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;

public class PinCount {

  private final int pinCount;
  private static final int MIN_PIN_COUNT = 0;
  private static final int MAX_PIN_COUNT = 10;
  private static final String INVALID_PIN_COUNT = "잘못된 수를 입력했습니다.";
  private static final String INVALID_TOTAL_PINCOUNT = "총 친 핀의 개수는 0개 이상 10개 이하여야 합니다.";

  public PinCount(int pinCount) {
    validatePinCount(pinCount);
    this.pinCount = pinCount;
  }

  private void validatePinCount(int pinCount) {
    if (pinCount < MIN_PIN_COUNT || pinCount > MAX_PIN_COUNT) {
      throw new IllegalArgumentException(INVALID_PIN_COUNT);
    }
  }

  public boolean isStrike() {
    return pinCount == MAX_PIN_COUNT;
  }

  public boolean isGutter() {
    return pinCount == MIN_PIN_COUNT;
  }

  public void validateNewPinCount(PinCount newPinCount) {
    if (newPinCount.validateRange(pinCount)) {
      throw new IllegalArgumentException(INVALID_TOTAL_PINCOUNT);
    }
  }

  private boolean validateRange(int originPinCount) {
    return (pinCount + originPinCount > MAX_PIN_COUNT || pinCount + originPinCount < MIN_PIN_COUNT);
  }


  public boolean totalSumIsTen(PinCount newPinCount) {
    return pinCount + newPinCount.getPinCount() == MAX_PIN_COUNT;
  }

  public int getPinCount() {
    return pinCount;
  }


}
