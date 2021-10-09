package bowling.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> values;

    private Players(List<Player> values) {
        this.values = values;
    }

    public static Players create() {
        return new Players(new ArrayList<>());
    }

    public static Players from(String... players) {
        return new Players(
                Arrays.stream(players)
                        .map(Player::from)
                        .collect(Collectors.toList())
        );
    }

    public void add(Player player) {
        values.add(player);
    }

    public List<Player> values() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(values, players1.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
