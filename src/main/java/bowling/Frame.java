package bowling;

public class Frame {

  private KnockedDownPins pins = new KnockedDownPins();

  /**
   * 최대 두 번 투구 가능
   * <p>
   * - 스트라이크 시 첫 번째 핀 10, 두 번째 핀 0
   * <p>
   * - 이외의 경우 초구 투구 시 첫 번째 핀 넘어뜨린 수, 두 번째 핀.isNull() = true
   * <p>
   * - 세 번째 투구 혹은 스트라이크 후 두 번째 투구 시 FrameOverException
   *
   * @param knockDownNum
   * @throws FrameOverException next()로 새로운 인스턴스 생성하여 처리
   */
  public void roll(int knockDownNum) throws FrameOverException {
    if (isOver()) {
      throw new FrameOverException();
    }

    pins = KnockedDownPinsFactory.createInstanceBy(pins, knockDownNum);
  }

  private boolean isOver() {
    return !pins.isSecondKnockDownNumNull();
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
