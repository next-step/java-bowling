package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Shot;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class GameController {
    public void run() {
        Game game = makeGame();

        showCurrentGameFrame(game);
        while(game.isNotFinished()) {
            int downCount = InputView.extractFrameResult(game.getCurrentFrameIndex());
            game.play(new Shot(downCount));
            showCurrentGameFrame(game);
        }
    }

    public Game makeGame() {
        String username = InputView.extractUsername();
        return Game.of(new User(username));
    }

    public void showCurrentGameFrame(Game game) {
        OutputView.showFrames();
        OutputView.showGameFrames(game);
    }
}
