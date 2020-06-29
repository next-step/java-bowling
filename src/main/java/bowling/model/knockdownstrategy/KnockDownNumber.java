package bowling.model.knockdownstrategy;

import bowling.model.KnockedDownPins;
import java.util.Objects;

public class KnockDownNumber {

  private Integer value;

  public KnockDownNumber() {
  }

  public KnockDownNumber(Integer value) {
    if (value < KnockedDownPins.MIN_NUMBER_OF_PINS) {
      throw new IllegalArgumentException(
          "쓰러트린 핀 개수는 " + KnockedDownPins.MIN_NUMBER_OF_PINS + "보다 작을 수 없습니다.");
    }

    if (KnockedDownPins.MAX_NUMBER_OF_PINS < value) {
      throw new IllegalArgumentException(
          "쓰러트린 핀 개수는 " + KnockedDownPins.MAX_NUMBER_OF_PINS + "보다 클 수 없습니다.");
    }

    this.value = value;
  }

    public int getIntValue() {
    return value == null ? 0 : value;
  }

  public boolean isNull() {
    return value == null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KnockDownNumber that = (KnockDownNumber) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "NumberOfKnockedDown{" +
        "value=" + value +
        '}';
  }
}
