package bowling;

import bowling.domain.Bowling;
import bowling.view.InputView;

public class BowlingGame {

  public static final int MAX_FRAME = 10;

  public static void main(String[] args) {
    String name = InputView.getName();
    Bowling bowling = new Bowling(name);

    System.out.println(bowling.getCurrent());
    while (bowling.getCurrent() <= MAX_FRAME) {
      int hitCount = InputView.getHitPinsCount(bowling.getCurrent());
      bowling.play(hitCount);
      System.out.println(bowling);
    }

    if (bowling.hasBonusPins()) {
      int hitCount = InputView.getHitPinsCount(bowling.getCurrent());
    }

  }

}
