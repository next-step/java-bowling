package bowling.domain;

import bowling.domain.frame.FrameNode;
import bowling.domain.frame.Frames;
import bowling.exception.CannotBowlException;

public class BowlingGame {

  private Player player;
  private Frames frames;

  public BowlingGame(Player player) {
    this.player = player;
    this.frames = new Frames();
  }

  public String getPlayerName() {
    return player.getName();
  }

  public FrameNode getCurrentFrame() {
    return frames.getCurrentFrame();
  }

  public FrameNode roll(int pinCount) throws CannotBowlException {
    return frames.roll(pinCount);
  }

  public FrameResults produceResults() {
    return frames.produceResults();
  }

  public boolean isEnd() {
    return frames.isEnd();
  }
}
