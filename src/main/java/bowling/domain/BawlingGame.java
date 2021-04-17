package bowling.domain;

public class BawlingGame {

  private Player player;
  private Frames frames;

  public BawlingGame(Player player, Frames frames) {
    this.player = player;
    this.frames = frames;
  }

  public BawlingGame(Player player) {
    this(player, new Frames());
  }

  public Frames getFrames() {
    return frames;
  }

  public boolean isEnd() {
    return frames.isEnd();
  }

  public void play(int pinCount) {
    frames.play(pinCount);
  }

  public int getFrameCount() {
    return frames.getIndex();
  }
}
