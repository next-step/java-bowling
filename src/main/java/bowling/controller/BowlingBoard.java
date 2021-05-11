package bowling.controller;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
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
    Player player = new Player(inputView.setupPlayer());
    board.addPlayerBoard(player);
    playGame();
  }

  private void setupBoard(){

  }

  private void playGame() {
    while (!board.checkFinished()) {
      playRound(board);
    }
  }

  private void playRound(Board board) {
    for (PlayerBoard playerBoard : board.playerBoards()) {
      playFrame(playerBoard);
    }
  }

  private void playFrame(PlayerBoard playerBoard) {
    int fallenPins = inputView.setupPins(playerBoard.round());
    playerBoard.addNewBall(new FallenPins(fallenPins));
    resultView.printBoard(playerBoard);
  }
}
