package bowling.domain;

public class Strike extends FrameState {

  public static final int BONUS_BOWL = 2;
  private static Strike instance = new Strike();

  private Strike() {
    super(BONUS_BOWL);
  }

  public static Strike getInstance() {
    return instance;
  }

  @Override
  public FrameState bowl(int pinCount) {
    return this;
  }
}
