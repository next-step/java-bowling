package bowling;

import bowling.view.InputView;

public class Controller {
    public void execute() {
        String playerName = InputView.requestPlayerName();
        GameService game = new GameService(playerName);
        ResultView.printResult(game.start());

        while (!game.isFinish()) {
            int number = game.getCurrentFrameNumber();
            int fallingPins = InputView.requestFallingPins(number);
            ResultView.printResult(game.throwBall(fallingPins));
        }
    }
}
