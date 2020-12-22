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
        Game game = new Game(names);

        return new GameController(game);
    }

    public void play() {
        List<GameOfPlayer> gameOfPlayers = game.getGameOfPlayers();
        while (!game.isGameEnd()) {
            playPlayers(gameOfPlayers);
        }

        OutputView.showGame(game);
    }

    private void playPlayers(List<GameOfPlayer> gameOfPlayers) {
        if (gameOfPlayers.isEmpty()) {
            return;
        }

        GameOfPlayer gameOfPlayer = gameOfPlayers.get(0);
        for (int idx = 0; idx < gameOfPlayers.size() && !gameOfPlayer.isGameEnd(); idx++) {
            gameOfPlayer = gameOfPlayers.get(idx);
            playFrame(gameOfPlayer);
        }
    }

    private void playFrame(GameOfPlayer gameOfPlayer) {
        boolean isFrameChanged = false;
        Frame frame;
        while (!isFrameChanged) {
            OutputView.showGame(game);
            int score = InputView.getScore(gameOfPlayer.getPlayer());
            frame = gameOfPlayer.getCurrentFrame();
            isFrameChanged = gameOfPlayer.playFrame(score, frame);
        }
    }
}
