package bowling.domain;

import bowling.exception.InvalidPlayersException;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        players.stream()
                .filter(player -> player.length() > 3)
                .findAny()
                .ifPresent(player -> {
                    throw new InvalidPlayersException(player);
                });
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public String name(int index) {
        return players.get(index).name();
    }

    public int size() {
        return players.size();
    }
}
