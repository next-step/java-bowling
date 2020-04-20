package bowling.game;

import bowling.Pin;
import bowling.Player;

import java.util.*;
import java.util.stream.Collectors;

public class BowlingGames {

    private final Map<Player, BowlingGame> games;

    private BowlingGames(final Map<Player, BowlingGame> games) {
        this.games = Collections.unmodifiableMap(games);
    }

    public static BowlingGames newInstance(final String... playerStrings) {
        List<Player> players = Arrays.stream(playerStrings)
                .map(Player::of)
                .collect(Collectors.toList());
        return newInstance(players);
    }

    public static BowlingGames newInstance(final List<Player> players) {
        Map<Player, BowlingGame> games = new LinkedHashMap<>();
        players.forEach(player -> games.put(player, BowlingGame.newInstance(player)));

        return new BowlingGames(games);
    }

    public void bowl(final Player player, final Pin pinCount) {
        BowlingGame bowlingGame = games.get(player);
        validateBowlingGame(bowlingGame);

        bowlingGame.bowl(pinCount);
    }

    private void validateBowlingGame(final BowlingGame bowlingGame) {
        if (Objects.isNull(bowlingGame)) {
            throw new IllegalStateException("Can not find player bowling game");
        }
    }

    public void prepareNextFrame() {
        games.keySet()
                .stream()
                .map(games::get)
                .forEach(BowlingGame::prepareNextFrame);
    }

    public boolean isRecentFrameOver(final Player player) {
        BowlingGame bowlingGame = games.get(player);

        if (Objects.isNull(bowlingGame)) {
            return true;
        }

        return bowlingGame.isRecentFrameOver();
    }

    public boolean isAllGameOver() {
        return games.keySet()
                .stream()
                .map(games::get)
                .allMatch(BowlingGame::isAllFramesOver);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(games.keySet());
    }

    public List<BowlingGame> getAllGames() {
        return games.keySet()
                .stream()
                .map(games::get)
                .collect(Collectors.toList());
    }
}
