package bowling.domain;

import bowling.domain.pin.Pin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean finished() {
        return players.stream()
                .allMatch(Player::finished);
    }

    public void bowl(Pin no) {
        currentBowler().bowl(no);
    }

    public String currentBowlerName() {
        return currentBowler().name();
    }

    private Player currentBowler() {
        return players.stream()
                .filter(player -> !player.finished())
                .min(Comparator.comparingInt(Player::currentFrame))
                .orElseThrow(IllegalStateException::new);
    }

    public List<String> names() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<List<String>> expressions() {
        return players.stream()
                .map(Player::expressions)
                .collect(Collectors.toList());
    }

    public List<List<Integer>> scores() {
        return players.stream()
                .map(Player::scores)
                .collect(Collectors.toList());
    }

    public int size() {
        return players.size();
    }
}
