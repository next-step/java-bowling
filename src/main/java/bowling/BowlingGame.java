package bowling;

import bowling.domain.Bowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

  private Bowling bowling;

  public BowlingGame(Bowling bowling) {
    this.bowling = bowling;
  }

  public static BowlingGame start() {
    String name = InputView.getName();
    Bowling bowling = new Bowling(name);
    ResultView.printScoreBoard(bowling);
    return new BowlingGame(bowling);
  }

  public void play() {
    while (!bowling.isEnd()) {
      int hitCount = InputView.getHitPinsCount(bowling.getCurrentFrameNumber());
      bowling.play(hitCount);
      ResultView.printScoreBoard(bowling);
    }

  }

}
