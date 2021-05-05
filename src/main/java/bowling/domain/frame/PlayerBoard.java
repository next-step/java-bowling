package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.turn.FallenPins;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoard {
  private static final int ZERO = 0;
  private static final int FIRST_ROUND = 1;
  private static final int FINAL_ROUND = 10;

  private Frame currentFrame;
  private final Player player;

  public PlayerBoard(Player player) {
    currentFrame = Frame.of(FIRST_ROUND);
    this.player = player;
  }

  public boolean checkFinished(){
    if(currentFrame.round() == FINAL_ROUND && currentFrame.checkFinished()){
      return true;
    }

    return false;
  }

  public Frame currentFrame(){
    return currentFrame;
  }

  public void addNewBall(FallenPins pins) {
    currentFrame = currentFrame.bowl(pins);
  }

  public String playerName() {
    return player.name();
  }
}
