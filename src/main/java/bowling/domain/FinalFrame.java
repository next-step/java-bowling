package bowling.domain;

public class FinalFrame extends Frame {

  private static final int FINAL_FRAME_MAX_SIZE = 3;
  private static final int MAX_PIN_COUNT = 30;
  private static final int SECOND_ROUND = 2;

  public FinalFrame() {
    super(Round.finalRound());
  }

  public static FinalFrame of() {
    return new FinalFrame();
  }

  protected void validateHitPin(int countOfHitPin) {
    if (pins.totalHitPin() + countOfHitPin > MAX_PIN_COUNT) {
      throw new IllegalArgumentException("핀의 갯수는 " + MAX_PIN_COUNT + "개를 넘을 수 없습니다.");
    }
  }

  public boolean isEndFrame() {
    if (pins.size() == SECOND_ROUND) {
      return pins.totalHitPin() < 10;
    }
    return pins.size() >= FINAL_FRAME_MAX_SIZE;
  }

  public boolean isLastFrame() {
    return isEndFrame();
  }
}
