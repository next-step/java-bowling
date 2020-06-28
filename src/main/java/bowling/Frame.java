package bowling;

public class Frame {

  private KnockedDownPins pins = new KnockedDownPins();

  /**
   * 투구 시 마다 남은 핀 개수 감소
   * <p>
   * - 세 번째 투구 혹은 스트라이크 후 두 번째 투구 시 FrameOverException
   *
   * @param numberOfPinsKnockedDown
   * @throws FrameOverException next()로 새로운 인스턴스 생성하여 처리
   */
  public void roll(int numberOfPinsKnockedDown) throws FrameOverException {
    if (isOver()) {
      throw new FrameOverException();
    }

    pins.knockDown(numberOfPinsKnockedDown);
  }

  private boolean isOver() {
    return !pins.isSecondKnockedDownNumNull();
  }

  public int getRemainingPinsNum() {
    return pins.getRemainingNum();
  }

  @Override
  public String toString() {
    return System.lineSeparator() + "Frame{" +
        "pins=" + pins +
        '}';
  }
}
