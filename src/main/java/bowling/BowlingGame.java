package bowling;

public class BowlingGame {

  Frame frame = NormalFrame.first();
  BowlingGameResult result = new BowlingGameResult();

  public Frame roll(int countOfPin) {
    Frame currentFrame = frame.roll(countOfPin);
    result.record(currentFrame.getFrameNo(), currentFrame.toString());
    frame = currentFrame.nextFrame();
    return frame;
  }

  public Boolean isGameEnd() {
    return frame.isGameEnd();
  }

  public int currentFrameNo() {
    return frame.getFrameNo();
  }

  public BowlingGameResult getResult() {
    return result;
  }

  @Override
  public String toString() {
    return frame.toString();
  }
}
