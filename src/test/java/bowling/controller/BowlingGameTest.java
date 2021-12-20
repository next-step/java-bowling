package bowling.controller;

import bowling.domain.frame.Frame;
import bowling.domain.GameInfo;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.ResultView;
import org.junit.jupiter.api.RepeatedTest;

class BowlingGameTest {

    @RepeatedTest(1)
    void 게임을_시작한다() {
        BowlingGame game = BowlingGame.create("DDD");
        GameInfo gameInfo = game.currentGameInfo();
        while (!game.isGameEnd()) {
            Frame frame = gameInfo.run(new RandomPitchNumberStrategy());

            ResultView.showBoard(gameInfo, frame);
        }
    }
}
