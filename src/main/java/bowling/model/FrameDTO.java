package bowling.model;

import bowling.model.framestatus.FrameStatus;

public class FrameDTO {

  private final KnockedDownPins pins;
  private final FrameStatus frameStatus;

  public FrameDTO(KnockedDownPins pins, FrameStatus frameStatus) {
    this.pins = pins;
    this.frameStatus = frameStatus;
  }

  public boolean isBonusFrame() {
    return frameStatus.isBonus();
  }

  public KnockedDownPins getPins() {
    return pins;
  }

  public String getFrameResult() {
    return frameStatus.getResultBy(pins);
  }

  @Override
  public String toString() {
    return "FrameDTO{" +
        "pins=" + pins +
        ", frameStatus=" + frameStatus +
        '}';
  }
}
