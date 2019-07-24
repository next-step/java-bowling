package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.Map;

public class BowlingGameLauncher {

  private static void start() {
    Player player = InputView.askPlayerName();

    BowlingGame bowlingGame = new BowlingGame();
    while(!bowlingGame.isGameEnd()) {
      bowlingGame.roll(InputView.askFallDownCount(bowlingGame.currentFrameNo()));
      Map<Integer, String> result = bowlingGame.getResult();
      OutputView.initialBoardPrint(player,result);
    }
  }

  public static void main(String[] args) {
    start();
  }

}
