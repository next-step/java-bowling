package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Shot;
import bowling.view.InputView;
import bowling.view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;

    public GameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Game game = makeGame();

        showCurrentGameFrame(game);
        while(game.isNotFinished()) {
            int downCount = inputView.extractFrameResult(game.getCurrentFrameIndex());
            game.play(new Shot(downCount));
            showCurrentGameFrame(game);
        }
    }

    public Game makeGame() {
        String username = inputView.extractUsername();
        return new Game(username);
    }

    public void showCurrentGameFrame(Game game) {
        outputView.showFrames();
        outputView.showGameFrames(game);
    }
}
