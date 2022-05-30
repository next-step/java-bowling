package bowling.domain;

import java.util.Collections;
import java.util.List;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = Collections.unmodifiableList(bowlingGames);
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }

    public boolean hashNext() {
        return bowlingGames.stream()
                .noneMatch(BowlingGame::hasNext);
    }
}
