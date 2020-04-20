package bowling.domain;

public class BowlingGame {

  private Player player;
  private NormalFrame frame;

  public BowlingGame(Player player) {
    this.player = player;
    this.frame = NormalFrame.initialize();
  }

  public String getPlayerName() {
    return player.getName();
  }

  public NormalFrame getFrame() {
    return frame;
  }

}
