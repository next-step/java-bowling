package bowling;

import bowling.view.InputView;
import bowling.view.OutputView;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BowlingGameLauncher {

  private static void start() {
    Player player = InputView.askPlayerName();

    BowlingGame bowlingGame = new BowlingGame();
    while(!bowlingGame.isGameEnd()) {
      bowlingGame.roll(InputView.askFallDownCount(bowlingGame.currentFrameNo()));
      Map<Integer, String> result = bowlingGame.getResult();
      OutputView.initialBoardPrint(result);
    }
  }

}
