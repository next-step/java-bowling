package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Players {
    private final List<Player> users;

    public Players(List<Player> users) {
        this.users = users;
    }

    public void forEach(Consumer<? super Player> action) {
        users.forEach(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players = (Players) o;
        return Objects.equals(users, players.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }
}
