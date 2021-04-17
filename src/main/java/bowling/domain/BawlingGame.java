package bowling.domain;

public class BawlingGame {

  private Player player;
  private Frames frames;

  public BawlingGame(String name) {
    this(new Player(name), new Frames());
  }

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
}
