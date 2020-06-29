package bowling.model;

import bowling.model.knockdownstrategy.KnockDownNumber;
import java.util.Objects;

public class KnockedDownPins {

  public final static int MAX_NUMBER_OF_PINS = 10;
  public final static int MIN_NUMBER_OF_PINS = 0;

  private KnockDownNumber firstKnockDownNum = new KnockDownNumber();
  private KnockDownNumber secondKnockDownNum = new KnockDownNumber();

  public KnockedDownPins() {
  }

  private KnockedDownPins(KnockDownNumber firstKnockDownNum,
      KnockDownNumber secondKnockDownNum) {
    if (MAX_NUMBER_OF_PINS < firstKnockDownNum.getIntValue() + secondKnockDownNum.getIntValue()) {
      throw new IllegalArgumentException("쓰러트린 핀이 " + MAX_NUMBER_OF_PINS + "개 이상이 될 수 없습니다.");
    }

    this.firstKnockDownNum = firstKnockDownNum;
    this.secondKnockDownNum = secondKnockDownNum;
  }

  public static class KnockedDownPinsBuilder {

    private KnockDownNumber firstKnockDownNum;
    private KnockDownNumber secondKnockDownNum = new KnockDownNumber();

    public KnockedDownPinsBuilder(int firstKnockDownNum) {
      this.firstKnockDownNum = new KnockDownNumber(firstKnockDownNum);
    }

    public KnockedDownPinsBuilder secondKnockDownNum(int number) {
      secondKnockDownNum = new KnockDownNumber(number);
      return this;
    }

    public KnockedDownPins build() {
      return new KnockedDownPins(firstKnockDownNum, secondKnockDownNum);
    }
  }

  public static KnockedDownPinsBuilder getBuilder(int firstKnockDownNum) {
    return new KnockedDownPinsBuilder(firstKnockDownNum);
  }

  public int getFirstKnockDownNum() {
    return firstKnockDownNum.getIntValue();
  }

  public int getSecondKnockDownNum() {
    return secondKnockDownNum.getIntValue();
  }

  public int getRemainingNum() {
    return MAX_NUMBER_OF_PINS - firstKnockDownNum.getIntValue() - secondKnockDownNum.getIntValue();
  }

  public boolean isFirstKnockDownNumNull() {
    return firstKnockDownNum.isNull();
  }

  public boolean isSecondKnockDownNumNull() {
    return secondKnockDownNum.isNull();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KnockedDownPins that = (KnockedDownPins) o;
    return Objects.equals(firstKnockDownNum, that.firstKnockDownNum) &&
        Objects.equals(secondKnockDownNum, that.secondKnockDownNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstKnockDownNum, secondKnockDownNum);
  }

  @Override
  public String toString() {
    return "RemainingPins{" +
        "firstNumberOfKnockedDown=" + firstKnockDownNum +
        ", secondNumberOfKnockedDown=" + secondKnockDownNum +
        '}';
  }
}
