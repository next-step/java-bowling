package bowling.model;

import bowling.model.framestatus.FrameStatus;
import bowling.model.framestatus.RequiredFirstRoll;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class NormalFrame implements Frame {

  private KnockedDownPins pins = new KnockedDownPins();
  private FrameStatus frameStatus;

  public NormalFrame(int currentIndex) {
    this.frameStatus = new RequiredFirstRoll(currentIndex);
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
   * @param KnockDownNumber 넘어뜨린 개수
   * @throws FrameOverException 새로운 인스턴스 생성하여 처리
   */
  @Override
  public void roll(int KnockDownNumber) throws FrameOverException {
    if (isOver()) {
      throw new FrameOverException();
    }

    pins = KnockedDownPinsFactory.createInstanceBy(pins, KnockDownNumber);

    frameStatus = frameStatus.createNextStatusBy(pins);
  }

  @Override
  public boolean isOver() {
    return frameStatus.isOver();
  }

  @Override
  public int getRemainingPinsNumber() {
    return pins.getRemainingNumber();
  }

  @Override
  public List<Integer> getIndexOfScoredFrames() {
    return frameStatus.getIndexOfScoredFrames();
  }

  @Override
  public KnockedDownPins getPins() {
    return pins;
  }

  @Override
  public FrameStatus getFrameStatus() {
    return frameStatus;
  }

  @Override
  public int getScoreBy(List<Frame> frames) {
    int startIndex = getFirstIndexOfScoredFrames();
    int lastIndex = getLastIndexOfScoredFrames();

    if (frames.size() < lastIndex) {
      lastIndex = frames.size();
    }

    return IntStream.range(startIndex, lastIndex)
        .map(index -> frames.get(index).getPins().getFirstKnockDownNumber())
        .sum() + frames.get(startIndex).getPins().getSecondKnockDownNumber();
  }

  private int getFirstIndexOfScoredFrames() {
    return frameStatus.getScoringFramesIndexes().get(0);
  }

  private int getLastIndexOfScoredFrames() {
    return frameStatus.getScoringFramesIndexes().get(0) + frameStatus.getScoringFramesIndexes()
        .size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NormalFrame normalFrame = (NormalFrame) o;
    return pins.equals(normalFrame.pins);
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
