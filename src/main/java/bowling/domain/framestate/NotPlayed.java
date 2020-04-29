package bowling.domain.framestate;

import static bowling.domain.PinCount.MAX_PIN;

public class NotPlayed extends FrameState {

  private static final int BONUS_BOWL = 0;
  private static final NotPlayed instance = new NotPlayed();

  private NotPlayed() {
    super(BONUS_BOWL);
  }

  public static NotPlayed getInstance() {
    return instance;
  }

  @Override
  public FrameState bowl(int pinCount) {
    if (pinCount == MAX_PIN) {
      return Strike.getInstance();
    }

    return new InProgress(pinCount);
  }
}
