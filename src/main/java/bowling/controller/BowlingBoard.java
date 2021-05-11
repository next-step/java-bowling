package bowling.controller;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.PlayerBoard;
import bowling.domain.turn.FallenPins;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingBoard {
  private final InputView inputView;
  private final ResultView resultView;
  private final Board board;

  public BowlingBoard() {
    inputView = new InputView();
    resultView = new ResultView();
    board = new Board();
  }

  public void run() {
    setupBoard();
    playGame();
  }

  private void setupBoard() {
    int playersCount = inputView.initPlayersCount();
    for (int i = 0; i < playersCount; i++) {
      insertPlayers(i + 1);
    }
    printBoards();
  }

  private void insertPlayers(int playerCount) {
    Player player = new Player(inputView.setupPlayer(playerCount));
    board.addPlayerBoard(player);
  }

  private void playGame() {
    while (!board.checkFinished()) {
      playRound();
    }
  }

  private void playRound() {
    for (PlayerBoard playerBoard : board.playerBoards()) {
      playFrame(playerBoard, board.runningFrame());
    }
    board.addRound();
  }

  private void playFrame(PlayerBoard playerBoard, int round) {
    while (playerBoard.checkTargetRoundFinished(round)) {
      int fallenPins = inputView.setupPins(playerBoard.round());
      playerBoard.addNewBall(new FallenPins(fallenPins));
      printBoards();
    }
  }

  private void printBoards() {
    resultView.printFullBoard(board.playerBoards());
  }
}
