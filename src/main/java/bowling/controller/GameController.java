package bowling.controller;

import bowling.domain.Game;
import bowling.domain.GameOfPlayer;
import bowling.domain.Player;
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
        Game game = new Game(IntStream.range(0, playerNum)
            .mapToObj(idx -> new GameOfPlayer(new Player(InputView.getName())))
            .collect(Collectors.toList()));

        return new GameController(game);
    }

    public void play() {
        List<GameOfPlayer> gameOfPlayers = game.getGameOfPlayers();
        while (!game.isGameEnd()) {
            playPlayers(gameOfPlayers);
        }

        OutputView.showGameOfPlayers(gameOfPlayers);
    }

    private void playPlayers(List<GameOfPlayer> gameOfPlayers) {
        for (GameOfPlayer gameOfPlayer : gameOfPlayers) {
            OutputView.showGameOfPlayers(gameOfPlayers);
            if (gameOfPlayer.isGameEnd())
                return;

            int score = InputView.getScore(gameOfPlayer.getPlayer());
            gameOfPlayer.bowl(score);
        }
    }
}
