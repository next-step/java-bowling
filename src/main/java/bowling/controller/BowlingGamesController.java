package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.frame.FrameNode;
import bowling.domain.Players;
import bowling.exception.CannotBowlException;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGamesController {

  private InputView inputView;

  public BowlingGamesController(InputView inputView) {
    this.inputView = inputView;
  }

  public void play() throws CannotBowlException {
    Players players = inputView.acceptPlayers();
    BowlingGames bowlingGames = BowlingGames.of(players);

    while (!bowlingGames.isEnd()) {
      playOneRound(bowlingGames);
    }
  }

  private void playOneRound(BowlingGames bowlingGames) throws CannotBowlException {
    for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
      playOneFrame(bowlingGames, bowlingGame);
    }
  }

  private void playOneFrame(BowlingGames bowlingGames, BowlingGame bowlingGame)
      throws CannotBowlException
  {
    FrameNode currentFrame = bowlingGame.getCurrentFrame();

    while (!currentFrame.isFinished()) {
      int pinCount = inputView.inputPinCount(bowlingGame.getPlayerName());
      bowlingGame.roll(pinCount);
      ResultView.printBowlingScoreTable(bowlingGames);
    }
  }
}
