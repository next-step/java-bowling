package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameLauncher {

  private static void start() {
    Player player = InputView.askPlayerName();

    BowlingGame bowlingGame = new BowlingGame();
    while (!bowlingGame.isGameEnd()) {
      bowlingGame.roll(InputView.askFallDownCount(bowlingGame.currentFrameNo()));
      BowlingGameResult gameResult = bowlingGame.getResult();
      OutputView.initialBoardPrint(player, gameResult);
    }
  }

  public static void main(String[] args) {
    start();
  }

}
