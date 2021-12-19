package bowling;

import bowling.controller.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.GameInfo;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Collections;

public class BowlingGameApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        ResultView.printFrame(name, Collections.emptyList());

        BowlingGame game = BowlingGame.create(name);
        while (!game.isGameEnd()) {
            GameInfo gameInfo = game.currentGameInfo();
            Frame frame = gameInfo.run(new RandomPitchNumberStrategy());

            ResultView.showBoard(gameInfo, frame);
        }
    }
}
