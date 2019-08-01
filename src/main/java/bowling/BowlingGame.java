package bowling;


import java.util.Objects;

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
    return frames.getResult(playerName);
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

  @Override
  public String toString() {
    return "BowlingGame{" +
        "frames=" + frames +
        ", playerName='" + playerName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BowlingGame game = (BowlingGame) o;
    return Objects.equals(frames, game.frames) &&
        Objects.equals(playerName, game.playerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(frames, playerName);
  }
}
