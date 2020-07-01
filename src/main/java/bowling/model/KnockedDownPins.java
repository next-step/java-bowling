package bowling.model;

import java.util.Objects;

public class KnockedDownPins {

  public final static int MAX_NUMBER_OF_PINS = 10;
  public final static int MIN_NUMBER_OF_PINS = 0;

  private KnockDownNumber firstKnockDownNumber = new KnockDownNumber();
  private KnockDownNumber secondKnockDownNumber = new KnockDownNumber();

  public KnockedDownPins() {
  }

  private KnockedDownPins(KnockDownNumber firstKnockDownNumber,
      KnockDownNumber secondKnockDownNumber) {
    if(firstKnockDownNumber == null) {
      throw new IllegalStateException("firstKnockDownNumber가 입력되지 않았습니다.");
    }

    int totalKnockDownNumber =
        firstKnockDownNumber.getIntValue() + secondKnockDownNumber.getIntValue();

    if (MAX_NUMBER_OF_PINS < totalKnockDownNumber) {
      throw new IllegalArgumentException("쓰러트린 핀이 " + MAX_NUMBER_OF_PINS + "개 이상이 될 수 없습니다.");
    }

    this.firstKnockDownNumber = firstKnockDownNumber;
    this.secondKnockDownNumber = secondKnockDownNumber;
  }

  public static class KnockedDownPinsBuilder {

    private KnockDownNumber firstKnockDownNumber;
    private KnockDownNumber secondKnockDownNumber = new KnockDownNumber();

    public KnockedDownPinsBuilder() {
    }

    public KnockedDownPinsBuilder firstKnockDownNumber(int firstKnockDownNumber) {
      this.firstKnockDownNumber = new KnockDownNumber(firstKnockDownNumber);
      return this;
    }

    public KnockedDownPinsBuilder secondKnockDownNumber(int number) {
      secondKnockDownNumber = new KnockDownNumber(number);
      return this;
    }

    public KnockedDownPins build() {
      return new KnockedDownPins(firstKnockDownNumber, secondKnockDownNumber);
    }
  }

  public static KnockedDownPinsBuilder getBuilder() {
    return new KnockedDownPinsBuilder();
  }

  public int getFirstKnockDownNumber() {
    return firstKnockDownNumber.getIntValue();
  }

  public int getSecondKnockDownNumber() {
    return secondKnockDownNumber.getIntValue();
  }

  public int getRemainingNumber() {
    int totalKnockDownNumber =
        firstKnockDownNumber.getIntValue() + secondKnockDownNumber.getIntValue();
    return MAX_NUMBER_OF_PINS - totalKnockDownNumber;
  }

  public boolean isFirstKnockDownNumberNull() {
    return firstKnockDownNumber.isNull();
  }

  public boolean isSecondKnockDownNumNull() {
    return secondKnockDownNumber.isNull();
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
    return Objects.equals(firstKnockDownNumber, that.firstKnockDownNumber) &&
        Objects.equals(secondKnockDownNumber, that.secondKnockDownNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstKnockDownNumber, secondKnockDownNumber);
  }

  @Override
  public String toString() {
    return "RemainingPins{" +
        "firstNumberOfKnockedDown=" + firstKnockDownNumber +
        ", secondNumberOfKnockedDown=" + secondKnockDownNumber +
        '}';
  }
}
