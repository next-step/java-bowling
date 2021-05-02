package bowling;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

  private static final InputView INPUT_VIEW = new InputView();
  private static final ResultView RESULT_VIEW = new ResultView();

  public BowlingGame() {
  }

  public void run() {
    Player player = new Player(INPUT_VIEW.getName());
    Bowling bowling = new Bowling(player);
    RESULT_VIEW.printScoreBoard(bowling);

    while (!bowling.isEnd()) {
      int hitPinsCount = INPUT_VIEW.getHitPinsCount(bowling.getCurrent());
      bowling.play(hitPinsCount);
      RESULT_VIEW.printScoreBoard(bowling);
    }
  }
}
