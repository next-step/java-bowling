package bowling.controller;

import bowling.domain.Board;
import bowling.domain.Player;
import bowling.domain.turn.FallenPins;
import bowling.view.InputView;

import java.util.stream.IntStream;

public class BowlingBoard {
  private static final int FIRST_ROUND = 1;

  private final InputView inputView;
  private final Board board;


  public BowlingBoard(){
    inputView = new InputView();
    board = new Board();
  }

  public void run() {
    Player player = new Player(inputView.setupPlayer());
    board.addPlayer(player);
    playGame();
  }

  private void playGame(){
    while(!board.checkFinished()){
      int fallenPins = inputView.setupPins(board.runningFrame());
      board.addNewBall(new FallenPins(fallenPins));
    }
  }


}
