package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = new ArrayList<>(players);
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    private void validate(List<Player> players) {
        if (Objects.isNull(players)) {
            throw new IllegalArgumentException("전달된 플레이어들이 null 입니다.");
        }
        if (players.isEmpty()) {
            throw new IllegalArgumentException("전달된 플레이어들이 비어있습니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
