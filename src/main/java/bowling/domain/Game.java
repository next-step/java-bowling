package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final List<GameOfPlayer> gameOfPlayers;

    public Game(List<String> names) {
        this.gameOfPlayers = names.stream()
            .map(name -> new GameOfPlayer(new Player(name)))
            .collect(Collectors.toList());
    }

    public boolean isGameEnd() {
        return gameOfPlayers.stream()
            .allMatch(GameOfPlayer::isGameEnd);
    }

    public List<GameOfPlayer> getGameOfPlayers() {
        return gameOfPlayers;
    }

    public List<Frame> getCurrentFrames() {
        return gameOfPlayers.stream()
            .map(GameOfPlayer::getCurrentFrame)
            .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return gameOfPlayers.stream()
            .map(GameOfPlayer::getPlayer)
            .collect(Collectors.toList());
    }
}
