package bowling.controller;

import bowling.domain.Game;
import bowling.domain.ScoreBoard;
import bowling.domain.Shot;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class GameController {
    public void run() {
        Game game = makeGame();
        ScoreBoard scoreBoard = new ScoreBoard();

        showCurrentGameFrame(game, scoreBoard);
        while(game.isNotFinished()) {
            int downCount = InputView.extractFrameResult(game.getCurrentFrameIndex());

            Shot shot = new Shot(downCount);
            scoreBoard.addPoint(shot, game.getCurrentFrameIndex());
            game.play(shot);
            showCurrentGameFrame(game, scoreBoard);
        }
    }

    public Game makeGame() {
        String username = InputView.extractUsername();
        return Game.of(new User(username));
    }

    public void showCurrentGameFrame(Game game, ScoreBoard scoreBoard) {
        OutputView.showFrames();
        OutputView.showGameFrames(game);
        OutputView.showScoreFrames(scoreBoard);
    }
}
