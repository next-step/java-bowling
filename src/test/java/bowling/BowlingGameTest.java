package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BowlingGameTest {

  @Test
  void roll() {
    BowlingGame game = new BowlingGame();
    while(!game.requiredBonusFrame()) {
      game.roll(1);
    }

    System.out.println(game.toString());
  }
}