package bowling;

public class LastFrame implements Frame {

  private static final int LAST_FRAME_NO = 10;

  private LastFallDownPins lastFallDownPins = new LastFallDownPins();

  public LastFrame roll(int countOfPin) {
    lastFallDownPins = lastFallDownPins.roll(countOfPin);
    return this;
  }

  @Override
  public boolean isGameEnd() {
    return lastFallDownPins.isLastFrameFinish();
  }

  @Override
  public int getFrameNo() {
    return LAST_FRAME_NO;
  }

  @Override
  public Frame nextFrame() {
    return this;
  }

  @Override
  public String toString() {
    return lastFallDownPins.toString();
  }
}
