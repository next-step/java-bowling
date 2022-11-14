package bowling.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class Players {

    private final Map<Position, Player> players;

    public Players(List<Player> players) {
        this.players = IntStream.range(0, players.size())
                .mapToObj((position) -> Map.entry(new Position(position), players.get(position)))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public boolean isFinish() {
        return players.values()
                .stream()
                .allMatch(Player::isFinish);
    }

    public Player findPlayerByPosition(Position position) {
        return players.get(position);
    }


    public Bowling findBowlingByUsername(Username username) {
        return players.values()
                .stream()
                .filter(player -> player.isSameUsername(username))
                .map(Player::getBowling)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public int size() {
        return players.size();
    }

    public List<Username> getUsernames() {
        return players.values()
                .stream()
                .map(Player::getUsername)
                .collect(Collectors.toList());
    }
}
