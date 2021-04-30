package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Marks> marks() {
        return players.stream()
                .map(Player::marks)
                .collect(Collectors.toList());
    }

    public List<Scores> scores() {
        return players.stream()
                .map(Player::scores)
                .collect(Collectors.toList());
    }

    public List<Name> names() {
        return players.stream()
                .map(Player::name)
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
