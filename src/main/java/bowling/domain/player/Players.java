package bowling.domain.player;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {

    private final List<Player> values;

    public Players(List<Player> values) {
        checkDuplicatedPlayer(values);
        this.values = values;
    }

    private void checkDuplicatedPlayer(List<Player> values) {
        HashSet<Player> players = new HashSet<>(values);
        if (players.size() != values.size()) {
            throw new IllegalArgumentException("플레이어 끼리 같은 이름은 사용할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(values);
    }

    public boolean isAllEnd() {
        return values.stream()
                .allMatch(Player::isGameEnd);
    }
}
