package bowling;

import bowling.frame.BowlingFrames;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BowlingGame {

    private final Map<Player, BowlingFrames> games;

    private BowlingGame(final Map<Player, BowlingFrames> games) {
        this.games = new LinkedHashMap<>(games);
    }

    public static BowlingGame newInstance(String... names) {
        List<Player> players = Arrays.stream(names)
                .map(Player::of)
                .collect(Collectors.toList());

        return newInstance(players);
    }

    public static BowlingGame newInstance(final List<Player> players) {
        Map<Player, BowlingFrames> games = new LinkedHashMap<>();
        players.forEach(player -> games.put(player, BowlingFrames.newInstance()));
        return new BowlingGame(games);
    }

    public boolean isOver() {
        return getAllBowlingFrames().stream()
                .allMatch(BowlingFrames::isAllFrameOver);
    }

    private List<BowlingFrames> getAllBowlingFrames() {
        return games.keySet()
                .stream()
                .map(games::get)
                .collect(Collectors.toList());
    }

    public void bowl(final Player player, final Pin pinCount) {
        BowlingFrames playerFrames = games.get(player);
        playerFrames.bowl(pinCount);
    }

    public int getFrameCount(final Player player) {
        BowlingFrames playerFrames = games.get(player);
        return playerFrames.size();
    }

    public Player getNextTurn() {
    }
}
