package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;

public class GameLauncher {

  public static void main(String[] args) {
    GameLauncher.start();
  }

  private static void start() {
    Player player = InputView.askPlayerName();

    BowlingGame bowlingGame = new BowlingGame(player.toString());
    while (!bowlingGame.isGameEnd()) {
      bowlingGame.bowl(InputView.askFallDownCount(bowlingGame.currentFrameNo()));
      GameResult gameResult = bowlingGame.result();
      OutputView.initialBoardPrint(player, gameResult);
    }
  }

}
