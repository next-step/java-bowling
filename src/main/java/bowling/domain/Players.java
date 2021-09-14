package bowling.domain;

import bowling.exception.PlayerCountOutOfBoundsException;
import bowling.exception.SameNamePlayerExistException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final LinkedHashSet<Player> players;

    public Players(final List<String> names) {
        players = names.stream()
                .map(Player::new)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        requireDifferentNames(names);
    }

    private void requireDifferentNames(final List<String> names) {
        if (players.size() != names.size()) {
            throw new SameNamePlayerExistException();
        }
    }

    public List<String> names() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public String getName(final int order) {
        requireValidOrder(order);
        return names().get(order);
    }

    public int count() {
        return players.size();
    }

    private void requireValidOrder(final int order) {
        if (order > count() - 1) {
            throw new PlayerCountOutOfBoundsException(count() - 1, order);
        }
    }
}

