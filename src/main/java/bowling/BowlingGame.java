package bowling;


public class BowlingGame {

  private Frames frames;

  public BowlingGame() {
    this.frames = new Frames();
  }

  public Frame bowl(int countOfPin) {
    return frames.bowl(countOfPin);
  }

  public GameResult result() {
    return frames.getResult();
  }

  public boolean isGameEnd() {
    return frames.isGameEnd();
  }

  public int currentFrameNo() {
    return frames.currentFrameNo();
  }
}
