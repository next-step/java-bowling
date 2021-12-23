package bowling;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> values;

    public Players(List<String> playersName) {
        this.values = playersName.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> value() {
        return Collections.unmodifiableList(values);
    }

    public boolean isEndGame() {
        return !values.stream()
                .filter(player -> !player.isEndGame())
                .findAny()
                .isPresent();
    }
}
