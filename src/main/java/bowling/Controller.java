package bowling;

import bowling.view.InputView;

public class Controller {
    public void execute() {
        String playerName = InputView.requestPlayerName();
        Game game = new Game(playerName);
        ResultView.printResult(game.start());

        while (!game.isFinish()) {
            int number = game.getCurrentFrameNumber();
            int fallingPins = InputView.requestFallingPins(number);
            GameStatus gameStatus = game.throwBall(fallingPins);
            ResultView.printResult(gameStatus);
        }
    }
}
