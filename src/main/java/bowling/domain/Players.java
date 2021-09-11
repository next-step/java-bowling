package bowling.domain;

import bowling.exception.InvalidPlayersException;

import java.util.List;

public class Players {
    private final List<String> players;

    public Players(List<String> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<String> players) {
        players.stream()
                .filter(player -> player.length() > 3)
                .findAny()
                .ifPresent(player -> {
                    throw new InvalidPlayersException(player);
                });
    }

    public static Players of(List<String> players) {
        return new Players(players);
    }

    public String name(int index) {
        return players.get(index);
    }

    public int size() {
        return players.size();
    }
}
