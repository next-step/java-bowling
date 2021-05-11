package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.turn.FallenPins;

public class PlayerBoard {
  private static final int FIRST_ROUND = 1;
  private static final int FINAL_ROUND = 10;
  private final Frame firstFrame;
  private final Player player;
  private Frame currentFrame;

  public PlayerBoard(Player player) {
    firstFrame = FrameFactory.of(FIRST_ROUND);
    currentFrame = firstFrame;
    this.player = player;
  }

  public boolean checkFinished() {
    return currentFrame.round() == FINAL_ROUND && currentFrame.checkFinished();
  }

  public void addNewBall(FallenPins pins) {
    currentFrame = currentFrame.bowl(pins);
  }

  public boolean checkTargetRoundFinished(int round) {
    return !checkFinished() && round() == round;
  }

  public int round() {
    return currentFrame.round();
  }

  public String playerName() {
    return player.name();
  }

  public Frame firstFrame() {
    return firstFrame;
  }

  public boolean checkCurrentFrameReady() {
    return currentFrame.checkReady();
  }
}
