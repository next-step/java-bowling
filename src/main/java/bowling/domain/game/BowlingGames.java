package bowling.domain.game;

import bowling.domain.player.Player;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlingGames {

    private final List<BowlingGame> games;

    public BowlingGames(List<Player> players) {
        this.games = players.stream()
            .map(BowlingGame::start)
            .collect(Collectors.toList());
    }

    public List<BowlingGame> getGames() {
        return games;
    }

    public boolean isAllGameFinished() {
        return games.stream()
            .allMatch(BowlingGame::isFinished);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BowlingGames that = (BowlingGames) o;
        return Objects.equals(games, that.games);
    }

    @Override
    public int hashCode() {
        return Objects.hash(games);
    }
}
