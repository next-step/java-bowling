package bowling.domain;

import java.util.Iterator;
import java.util.List;

public class PlayerNames implements Iterable<PlayerName> {
    private final List<PlayerName> value;

    private PlayerNames(List<PlayerName> value) {
        this.value = value;
    }

    public static PlayerNames of(List<PlayerName> value) {
        return new PlayerNames(value);
    }

    @Override
    public Iterator<PlayerName> iterator() {
        return value.iterator();
    }
}
