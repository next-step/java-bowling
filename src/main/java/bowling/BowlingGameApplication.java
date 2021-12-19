package bowling;

import bowling.controller.BowlingGame;
import bowling.domain.GameInfo;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayerName();

        BowlingGame game = BowlingGame.create(name);
        while (!game.isGameEnd()) {
            GameInfo gameInfo = game.currentGameInfo();
            gameInfo.run(new RandomPitchNumberStrategy());

            ResultView.showBoard(gameInfo);
        }
    }
}
