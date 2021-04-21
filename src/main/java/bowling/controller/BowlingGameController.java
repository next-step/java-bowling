package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.Pin;
import bowling.view.InputView;
import bowling.view.ResultView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGameController {

  private final InputView inputView = new InputView();
  private final ResultView resultView = new ResultView();

  public void run() {

    int playerCount = inputView.inputPlayerCount();

    List<String> playerNames = IntStream.range(0, playerCount)
        .mapToObj(index -> inputView.inputName())
        .collect(Collectors.toList());

    BowlingGames bowlingGames = new BowlingGames(playerNames);

    resultView.printEmptyResult(playerNames);

    while (!bowlingGames.isEnd()) {
      playBowlingGame(bowlingGames);
    }

  }

  private void playBowlingGame(BowlingGames bowlingGames) {
    for (BowlingGame bowlingGame : bowlingGames.getBowlingGames()) {
      play(bowlingGames, bowlingGame);
    }
  }

  private void play(BowlingGames bowlingGames, BowlingGame bowlingGame) {
    int currentFrameCount = bowlingGame.getFrameCount();

    while (bowlingGame.isFrameCount(currentFrameCount)) {
      int count = inputView.inputPinCountByPlayer(bowlingGame.getPlayer());
      bowlingGame.play(new Pin(count));
      resultView.printResult(bowlingGames);
    }
  }
  
}
