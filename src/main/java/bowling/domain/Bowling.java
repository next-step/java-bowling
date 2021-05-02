package bowling.domain;

import java.util.List;

public class Bowling {

  private Player player;
  private Frames frames;

  public Bowling(Player player) {
    this.player = player;
    this.frames = new Frames();
  }

  public List<String> play(int hitCount) {
    return frames.play(hitCount);
  }

  public String getPlayerName() {
    return player.getName();
  }

  public List<String> getScores() {
    return frames.result();
  }

  public int getCurrent() {
    return frames.getCurrent();
  }

  public boolean isEnd() {
    return frames.isEnd();
  }
}
