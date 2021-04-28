package bowling.domain.frame;

public class FinalFrame extends Frame {

  private static final int FINAL_FRAME_MAX_SIZE = 3;
  private static final int BONUS_MIN_PIN_COUNT = 10;
  private static final int FINAL_MAX_PIN_COUNT = 30;
  private static final int SECOND_ROUND = 2;

  public static FinalFrame of() {
    return new FinalFrame();
  }

  protected void validateHitPin(int countOfHitPin) {
    if (pins.totalHitPin() + countOfHitPin > FINAL_MAX_PIN_COUNT) {
      throw new IllegalArgumentException("핀의 갯수는 " + FINAL_MAX_PIN_COUNT + "개를 넘을 수 없습니다.");
    }
  }

  public boolean isEndFrame() {
    if (pins.size() == SECOND_ROUND) {
      return pins.totalHitPin() < BONUS_MIN_PIN_COUNT;
    }
    return pins.size() >= FINAL_FRAME_MAX_SIZE;
  }
}
