package bowling.refactoring.domain;


import java.util.*;
import java.util.stream.*;

public class BowlingGame {
    private final List<Player> players;

    public BowlingGame(List<String> names) {
        this.players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> players() {
        return new ArrayList<>(players);
    }
}
