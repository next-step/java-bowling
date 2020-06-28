package bowling;

import java.util.Objects;

public class Frame {

  private KnockedDownPins pins = new KnockedDownPins();
  private FrameStatus frameStatus;

  public Frame(int curIndex) {
    this.frameStatus = new RequiredFirstRoll(curIndex);
  }

  /**
   * 최대 두 번 투구 가능
   * <p>
   * - 스트라이크 시 첫 번째 핀 10, 두 번째 핀 0
   * <p>
   * - 이외의 경우 초구 투구 시 첫 번째 핀 넘어뜨린 수, 두 번째 핀.isNull() = true
   * <p>
   * - 세 번째 투구 혹은 스트라이크 후 두 번째 투구 시 FrameOverException
   *
   * @param knockDownNum 넘어뜨린 개수
   * @throws FrameOverException 새로운 인스턴스 생성하여 처리
   */
  public void roll(int knockDownNum) throws FrameOverException {
    if (isOver()) {
      throw new FrameOverException();
    }

    pins = KnockedDownPinsFactory.createInstanceBy(pins, knockDownNum);

    frameStatus = frameStatus.createNextStatusBy(pins);
  }

  public boolean isOver() {
    return frameStatus.isOver();
  }

  public int getRemainingPinsNum() {
    return pins.getRemainingNum();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Frame frame = (Frame) o;
    return pins.equals(frame.pins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pins);
  }

  @Override
  public String toString() {
    return System.lineSeparator() + "Frame{" +
        "pins=" + pins +
        '}';
  }
}
