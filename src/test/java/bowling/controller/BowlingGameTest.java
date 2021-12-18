package bowling.controller;

import bowling.domain.Frame;
import bowling.domain.Player;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.ResultView;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

class BowlingGameTest {

    @RepeatedTest(100)
    void 게임을_시작한다() {
        BowlingGame game = BowlingGame.init();
        List<Frame> frames = game.run(new RandomPitchNumberStrategy());
        ResultView.showBoard(Player.from("DDD"), frames);
    }
}