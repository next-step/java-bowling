package bowling.domain;

import bowling.domain.frame.FrameNode;
import bowling.domain.frame.NormalFrame;
import bowling.exception.CannotBowlException;

public class BowlingGame {

  private Player player;
  private NormalFrame initialFrame;
  private FrameNode currentFrame;

  public BowlingGame(Player player) {
    this.player = player;
    this.initialFrame = NormalFrame.initialize();
    this.currentFrame = this.initialFrame;
  }

  public String getPlayerName() {
    return player.getName();
  }

  public NormalFrame getInitialFrame() {
    return initialFrame;
  }

  public FrameNode getCurrentFrame() {
    return currentFrame;
  }

  public FrameNode roll(int pinCount) throws CannotBowlException {
    currentFrame = currentFrame.roll(pinCount);
    return currentFrame;
  }

  public boolean isEnd() {
    return currentFrame.isFinalFrame() && currentFrame.isFinished();
  }
}
