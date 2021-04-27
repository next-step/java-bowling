package bowling;

import bowling.domain.Bowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

  public static final int MAX_FRAME = 10;

  public static void main(String[] args) {
    Bowling bowling = initGame();
    playBowling(bowling);
    if (bowling.hasBonus()) {
      playBonus(bowling);
    }

  }

  private static Bowling initGame() {
    String name = InputView.getName();
    Bowling bowling = new Bowling(name);
    ResultView.printScoreBoard(bowling);
    return bowling;
  }

  private static void playBowling(Bowling bowling) {
    while (bowling.getCurrent() <= MAX_FRAME) {
      int hitCount = InputView.getHitPinsCount(bowling.getCurrent());
      bowling.play(hitCount);
      ResultView.printScoreBoard(bowling);
    }
  }

  private static void playBonus(Bowling bowling) {
    int bonus = InputView.getBonusHitPinsCount();
    ResultView.printBonus(bowling, bonus);
  }

}
