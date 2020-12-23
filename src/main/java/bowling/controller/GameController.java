package bowling.controller;

import bowling.domain.Game;
import bowling.domain.GameOfPlayer;
import bowling.domain.frame.Frame;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameController {

    private final Game game;

    private GameController(Game game) {
        this.game = game;
    }

    public static GameController start() {
        int playerNum = InputView.getPlayerNum();
        List<String> names = IntStream.range(0, playerNum)
            .mapToObj(idx -> InputView.getName(idx + 1))
            .collect(Collectors.toList());

        return new GameController(new Game(names));
    }

    public void play() {
        List<Frame> firstFrames = game.getCurrentFrames();
        while (!game.isGameEnd()) {
            playPlayers(firstFrames);
        }

        OutputView.showGame(firstFrames, game.getPlayers());
    }

    private void playPlayers(List<Frame> firstFrames) {
        for (GameOfPlayer gameOfPlayer : game.getGameOfPlayers()) {
            playFrame(firstFrames, gameOfPlayer);
        }
    }

    private void playFrame(List<Frame> firstFrames, GameOfPlayer gameOfPlayer) {
        if (gameOfPlayer.isGameEnd()) {
            return;
        }

        Frame frame = gameOfPlayer.getCurrentFrame();
        while (!isFrameEnd(gameOfPlayer, frame)) {
            OutputView.showGame(firstFrames, game.getPlayers());
            int score = InputView.getScore(gameOfPlayer.getPlayer());
            frame = gameOfPlayer.playFrame(score);
        }
    }

    private boolean isFrameEnd(GameOfPlayer gameOfPlayer, Frame frame) {
        if (frame != gameOfPlayer.getCurrentFrame()) {
            return true;
        }
        return gameOfPlayer.isGameEnd();
    }
}
