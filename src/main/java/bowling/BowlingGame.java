package bowling;

public class BowlingGame {

  private Frames frames;

  public BowlingGame() {
    frames = new Frames();
  }

  public Frame roll(int countOfPin) {
    return frames.roll(countOfPin);
  }

  public Boolean isGameEnd() {
    return frames.isGameEnd();
  }

  public int currentFrameNo() {
    return frames.getFrameNo();
  }

  public BowlingGameResult getResult() {
    return frames.getResult();
  }

  @Override
  public String toString() {
    return frames.toString();
  }

}
