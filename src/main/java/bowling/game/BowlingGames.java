package bowling.game;

import bowling.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {

    private final List<BowlingGame> games;

    private BowlingGames(final List<BowlingGame> games) {
        this.games = Collections.unmodifiableList(games);
    }

    public static BowlingGames newInstance(final String... playerStrings) {
        List<Player> players = Arrays.stream(playerStrings)
                .map(Player::of)
                .collect(Collectors.toList());
        return newInstance(players);
    }

    public static BowlingGames newInstance(final List<Player> players) {
        List<BowlingGame> games = players.stream()
                .map(BowlingGame::newInstance)
                .collect(Collectors.toList());

        return new BowlingGames(games);
    }

    public void prepareNextFrames() {
        games.forEach(BowlingGame::prepareNextFrame);
    }

    public boolean isAllGameOver() {
        return games.stream()
                .allMatch(BowlingGame::isAllFramesOver);
    }

    public BowlingGameTurn makeNewGameTurn() {
        return BowlingGameTurn.newInstance(games);
    }

    public List<BowlingGame> getGames() {
        return games;
    }
}
