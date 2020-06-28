package bowling;

public class RemainingPins {

  private final static int FIRST_NUMBER_OF_PINS = 10;

  private int remainingPins = FIRST_NUMBER_OF_PINS;
  private Integer firstNumberOfKnockedDown;

  public void knockDown(int numberOfPinsKnockedDown) {
    if (remainingPins - numberOfPinsKnockedDown < 0) {
      throw new IllegalArgumentException("쓰러트린 핀이 10개 이상이 될 수 없습니다.");
    }

    if (firstNumberOfKnockedDown == null) {
      firstNumberOfKnockedDown = numberOfPinsKnockedDown;
    }

    remainingPins -= numberOfPinsKnockedDown;
  }

  public int getRemainingPins() {
    return remainingPins;
  }

  public Integer getFirstNumberOfKnockedDown() {
    return firstNumberOfKnockedDown;
  }
}
