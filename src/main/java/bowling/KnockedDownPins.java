package bowling;

public class KnockedDownPins {

  public final static int MAX_NUMBER_OF_PINS = 10;

  private NumberOfKnockedDown firstKnockedDownNum = new NumberOfKnockedDown();
  private NumberOfKnockedDown secondKnockedDownNum = new NumberOfKnockedDown();

  public void knockDown(int numberOfPinsKnockedDown) {
    if (MAX_NUMBER_OF_PINS < firstKnockedDownNum.getValue() + numberOfPinsKnockedDown) {
      throw new IllegalArgumentException("쓰러트린 핀이 " + MAX_NUMBER_OF_PINS + "개 이상이 될 수 없습니다.");
    }

    if (!firstKnockedDownNum.isNull() && secondKnockedDownNum.isNull()) {
      secondKnockedDownNum = new NumberOfKnockedDown(numberOfPinsKnockedDown);
    }

    if (firstKnockedDownNum.isNull()) {
      firstKnockedDownNum = new NumberOfKnockedDown(numberOfPinsKnockedDown);

      if (numberOfPinsKnockedDown == 10) {
        secondKnockedDownNum = new NumberOfKnockedDown(0);
      }
    }
  }

  public NumberOfKnockedDown getFirstKnockedDownNum() {
    return firstKnockedDownNum;
  }

  public int getRemainingNum() {
    return MAX_NUMBER_OF_PINS - firstKnockedDownNum.getValue() - secondKnockedDownNum.getValue();
  }

  public boolean isSecondKnockedDownNumNull() {
    return secondKnockedDownNum.isNull();
  }

  @Override
  public String toString() {
    return "RemainingPins{" +
        "firstNumberOfKnockedDown=" + firstKnockedDownNum +
        ", secondNumberOfKnockedDown=" + secondKnockedDownNum +
        '}';
  }
}
