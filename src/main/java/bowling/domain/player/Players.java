package bowling.domain.player;

import bowling.exception.PlayerListNullPointerException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateNull(players);
        this.players = players;
    }

    private final void validateNull(final List<Player> players) {
        if (Objects.isNull(players)) {
            throw new PlayerListNullPointerException();
        }
    }

    public final Stream<Player> stream() {
        return players.stream();
    }

    public final boolean isAllFinish() {
        return stream().allMatch(Player::isFinish);
    }
}
