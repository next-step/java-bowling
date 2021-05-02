package bowling.domain.frame;

import bowling.domain.Player;
import bowling.domain.turn.FallenPins;

import java.util.ArrayList;
import java.util.List;

public class Round {
  private static final int ZERO = 0;
  private static final int FINAL_ROUND = 10;

  private final List<Frame> frames;
  private final Player player;

  public Round(Player player){
    frames = new ArrayList<>();
    this.player = player;
  }

  public List<Frame> frames() {
    return frames;
  }

  public void add(Frame frame){
    frames.add(frame);
  }

  public Frame tail() {
    return frames.get(frames.size() - 1);
  }

  public int size(){
    return frames.size();
  }

  public boolean isEmpty() {
    return frames.size()==ZERO;
  }

  public boolean checkCurrentRoundFinished(){
    return tail().checkFinished();
  }

  public boolean checkFinished() {
    if (size() == FINAL_ROUND && tail().checkFinished()) {
      return true;
    }
    return false;
  }

  public void addNewBall(FallenPins pins){
    Frame frame = tail();
    frame.shot(pins);
  }

  public String playerName(){
    return player.name();
  }
}
