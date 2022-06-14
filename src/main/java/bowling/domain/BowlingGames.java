package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingGames {

    private final List<BowlingGame> values;

    private BowlingGames(List<BowlingGame> values) {
        this.values = values;
    }

    public static BowlingGames initialize(List<Player> players) {
        List<BowlingGame> bowlingGames = new ArrayList<>();
        for (Player player : players) {
            bowlingGames.add(new BowlingGame(player));
        }
        return new BowlingGames(bowlingGames);
    }

    public List<BowlingGame> values() {
        return Collections.unmodifiableList(values);
    }
}
