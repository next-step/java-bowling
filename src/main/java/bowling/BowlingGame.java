package bowling;


public class BowlingGame {

  private Frames frames;
  private String playerName;

  public BowlingGame(String playerName) {
    this.frames = new Frames();
    this.playerName = playerName;
  }

  public Frames bowl(int countOfPin) {
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

  public boolean isYourGame(String playerName) {
    return this.playerName.equals(playerName);
  }
}
