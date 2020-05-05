package bowling.domain.framestate;

import static bowling.domain.PinCount.MAX_PIN;

public class InProgress extends FrameState {
  private static final int BONUS_BOWL = 0;

  private int pinCount;

  public InProgress(int pinCount) {
    super(BONUS_BOWL);

    this.pinCount = pinCount;
  }

  @Override
  public FrameState bowl(int pinCount) {
    if (this.pinCount + pinCount == MAX_PIN) {
      return Spare.getInstance();
    }

    return Miss.getInstance();
  }
}
