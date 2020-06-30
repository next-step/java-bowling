package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGames {
    private final List<BowlingGame> bowlingGamesList;

    public BowlingGames(List<BowlingGame> bowlingGamesList) {
        this.bowlingGamesList = new ArrayList<>(bowlingGamesList);
    }

    public static BowlingGames of(List<BowlingGame> bowlingGameList) {
        return new BowlingGames(bowlingGameList);
    }

    public int size() {
        return this.bowlingGamesList.size();
    }
}
