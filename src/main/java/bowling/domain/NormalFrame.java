package bowling.domain;

public class NormalFrame extends Frame {

  private static final int NORMAL_FRAME_MAX_SIZE = 2;
  private static final int MAX_PIN_COUNT = 10;

  public NormalFrame(Round round) {
    super(round);
  }

  protected void validateHitPin(int countOfHitPin) {
    if (pins.totalHitPin() + countOfHitPin > MAX_PIN_COUNT) {
      throw new IllegalArgumentException("핀의 갯수는 " + MAX_PIN_COUNT + "개를 넘을 수 없습니다.");
    }
  }

  private boolean isStrike() {
    return symbol() == ScoreSymbol.STRIKE;
  }

  public boolean isEndFrame() {
    return pins.size() >= NORMAL_FRAME_MAX_SIZE || isStrike();
  }

  public boolean isLastFrame() {
    return false;
  }

}
