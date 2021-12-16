package bowling.domain;

import bowling.strategy.RandomPitchNumberStrategy;
import org.junit.jupiter.api.RepeatedTest;

class BowlingGameTest {

    @RepeatedTest(100)
    void 게임을_시작한다() {
        BowlingGame game = BowlingGame.init();
        game.run(Player.from("ltw"), new RandomPitchNumberStrategy());
    }
}