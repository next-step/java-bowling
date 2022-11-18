package bowling.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {

        validDuplicate(players);
        this.players = players;
    }

    private void validDuplicate(final List<Player> values) {

        final Set<Player> players = new HashSet<>(values);
        if (players.size() != values.size()) {
            throw new IllegalArgumentException("플레이어 끼리 같은 이름은 사용할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {

        return Collections.unmodifiableList(players);
    }

    public boolean canEnd() {

        return players.stream()
                .allMatch(Player::isGameEnd);
    }
}
