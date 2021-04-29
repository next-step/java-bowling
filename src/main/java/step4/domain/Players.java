package step4.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Name> names() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<Frames> frames() {
        return players.stream()
                .map(Player::frames)
                .collect(Collectors.toList());
    }

    public List<Scores> scores() {
        return players.stream()
                .map(Player::scores)
                .collect(Collectors.toList());
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }

    public boolean isAllFinished() {
        return players.stream()
                .allMatch(Player::hasFinishedGame);
    }
}
