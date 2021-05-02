package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.turn.FallenPins;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private static final int FIRST_ROUND = 1;
  private static final int FINAL_ROUND = 10;

  private final List<Frame> frames;
  private final List<Player> players;

  public Board() {
    frames = new ArrayList<>();
    players = new ArrayList<>();
  }

  public void makeFirstFrame() {
    frames.add(Frame.of(FIRST_ROUND));
  }

  public List<Frame> frames() {
    return frames;
  }

  public void addingFrame() {
    frames.add(tail().makeNextRound());
  }

  private Frame tail() {
    return frames.get(frames.size() - 1);
  }

  public int size() {
    return frames.size();
  }

  public boolean checkFinished() {
    if (size() == FINAL_ROUND && tail().checkFinished()) {
      return true;
    }
    return false;
  }

  public int runningFrame() {
    Frame tail = tail();

    if (tail.checkFinished()) {
      frames.add(tail.makeNextRound());
    }

    return size();
  }

  public void addPlayer(Player player){
    players.add(player);
  }

  public void addAllPlayers(List<Player> allPlayers){
    players.addAll(allPlayers);
  }

  public void addNewBall(FallenPins pins){
    Frame frame = tail();
    frame.shot(pins);
  }
}
