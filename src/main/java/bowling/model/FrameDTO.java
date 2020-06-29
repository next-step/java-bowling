package bowling.model;

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

  @Override
  public String toString() {
    if (frameStatus instanceof Strike) {
      return "X";
    }

    if (frameStatus instanceof Spare) {
      return pins.getFirstKnockDownNum() + BAR + "/";
    }

    String left = String.valueOf(pins.getFirstKnockDownNum());
    String right = pins.isSecondKnockDownNumNull() ? "" : BAR + pins.getSecondKnockDownNum();

    return (left + right).replaceAll("0", GUTTER);
  }
}
