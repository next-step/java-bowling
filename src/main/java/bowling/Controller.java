package bowling;

import bowling.view.GameStatus;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class Controller {
    public void execute() {
        int count = InputView.requestPlayerCount();
        List<String> playerNames = InputView.requestPlayerNames(count);

        Game game = new Game(playerNames);
        List<GameStatus> status = game.start();
        ResultView.printResults(status);

        while (!game.isFinish()) {
            game.forEach(frame -> {
                int fallingPins = InputView.requestFallingPins(frame.getPlayerName());
                frame.throwBall(fallingPins);
                ResultView.printResults(status);
            });
        }
    }
}
