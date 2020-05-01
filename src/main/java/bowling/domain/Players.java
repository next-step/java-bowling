package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> players) {
        if (isPlayerNamesNullOrEmpty(players)) {
            throw new IllegalArgumentException("Create Players failed. players must have at least 1 player");
        }

        if (isPlayersHaveSameName(players)) {
            throw new IllegalArgumentException("Create Players failed. players are must have not same name");
        }

        return new Players(players.stream().map(Player::of).collect(Collectors.toList()));
    }

    static Players of(Player... players) {
        return new Players(Arrays.asList(players));
    }

    private static boolean isPlayersHaveSameName(List<String> players) {
        return players.stream().distinct().count() != players.size();
    }

    private static boolean isPlayerNamesNullOrEmpty(List<String> players) {
        return Objects.isNull(players) || players.isEmpty();
    }

    public Player getCurrentPlayer() {
        return players.stream()
                .min(Comparator.comparingInt(Player::getCurrentFrameNumber)
                        .thenComparingInt(Player::getCurrentFrameShotCount))
                .orElseThrow(() -> new IllegalStateException("players have at least 1 player"));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public boolean isGameSet() {
        return players.stream().allMatch(Player::isGameSet);
    }
}
