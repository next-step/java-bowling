package bowling.model;

import bowling.model.framestatus.Bonus;
import bowling.model.framestatus.FrameStatus;
import bowling.model.framestatus.Spare;
import bowling.model.framestatus.Strike;

public class FrameDTO {

  private final static String BAR = "|";
  private final static String GUTTER = "-";

  private KnockedDownPins pins;
  private final FrameStatus frameStatus;

  public FrameDTO(KnockedDownPins pins, FrameStatus frameStatus) {
    this.pins = pins;
    this.frameStatus = frameStatus;
  }

  public static FrameDTO createBy(Frame frame) {
    return new FrameDTO(frame.getPins(), frame.getFrameStatus());
  }

  public boolean isBonus() {
    return frameStatus instanceof Bonus;
  }

  @Override
  public String toString() {
    if (frameStatus instanceof Strike) {
      return "X";
    }

    if (frameStatus instanceof Spare) {
      return pins.getFirstKnockDownNum() + BAR + "/";
    }

    if (frameStatus instanceof Bonus && pins.getFirstKnockDownNum() == 10) {
      return "X";
    }

    String left = pins.isFirstKnockDownNumNull() ? "" : String.valueOf(pins.getFirstKnockDownNum());
    String right = pins.isSecondKnockDownNumNull() ? "" : BAR + pins.getSecondKnockDownNum();

    return (left + right).replaceAll("0", GUTTER);
  }
}
