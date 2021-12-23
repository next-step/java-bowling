package bowling;

import bowling.controller.BowlingGame;
import bowling.domain.GameInfo;
import bowling.domain.frame.Frame;
import bowling.strategy.RandomPitchNumberStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameApplication {
    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        ResultView.printFrame(name);

        BowlingGame game = BowlingGame.create(name);
        RandomPitchNumberStrategy numberStrategy = new RandomPitchNumberStrategy();
        while (!game.isGameEnd()) {
            GameInfo gameInfo = game.currentGameInfo();
            Frame frame = gameInfo.run(numberStrategy);

            ResultView.showBoard(gameInfo, frame);
        }
    }
}
