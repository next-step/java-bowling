package bowling.step2.domain;

import bowling.step2.exception.PlayerNameEmptyException;
import bowling.step2.exception.PlayerNameMaximumException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Players {
    private final List<PlayerName> players;

    private Players (List<PlayerName> players) {
        this.players = players;
    }

    public static Players of (List<PlayerName> players) {
        return new Players(players);
    }

    public Stream<PlayerName> stream () {
        return players.stream();
    }
}