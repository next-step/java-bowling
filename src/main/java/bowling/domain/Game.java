package bowling.domain;

import java.util.List;

public class Game {
    private final List<GameOfPlayer> gameOfPlayers;

    public Game(List<GameOfPlayer> gameOfPlayers) {
        this.gameOfPlayers = gameOfPlayers;
    }

    public boolean isGameEnd() {
        return gameOfPlayers.stream()
            .allMatch(GameOfPlayer::isGameEnd);
    }

    public List<GameOfPlayer> getGameOfPlayers() {
        return gameOfPlayers;
    }
}
