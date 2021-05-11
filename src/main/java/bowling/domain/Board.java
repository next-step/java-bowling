package bowling.domain;

import bowling.domain.frame.PlayerBoard;

import java.util.ArrayList;
import java.util.List;

public class Board {
  private static final int FIRST_ROUND = 1;

  private final List<PlayerBoard> playerBoards;
  private final int roundNumber;

  public Board() {
    playerBoards = new ArrayList<>();
    roundNumber = FIRST_ROUND;
  }

  public int size() {
    return playerBoards.size();
  }

  public List<PlayerBoard> playerBoards() {
    return playerBoards;
  }

  public int runningFrame() {
    return roundNumber;
  }

  public void addPlayerBoard(Player player) {
    PlayerBoard playerBoard = new PlayerBoard(player);
    playerBoards.add(playerBoard);
  }

  public boolean checkFinished() {
    long finishedCount = playerBoards.stream().filter(playerBoard -> playerBoard.checkFinished()).count();
    long fullSize = playerBoards.size();
    return finishedCount == fullSize;
  }
}
