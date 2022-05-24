package bowling.domain;

import java.util.Collections;
import java.util.List;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = Collections.unmodifiableList(bowlingGames);
    }

    public static BowlingGames create(List<BowlingGame> bowlingGames) {
        return new BowlingGames(bowlingGames);
    }

}
