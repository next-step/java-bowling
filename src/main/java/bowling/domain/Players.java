package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        names.forEach((name) -> players.add(new Player(name)));
    }
}
