package bowling.domain.player;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> values;

    public Players(List<Player> values) {
        this.values = values;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(values);
    }

    public boolean isAllEnd() {
        return values.stream()
                .allMatch(Player::isGameEnd);
    }
}
