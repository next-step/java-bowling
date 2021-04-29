package step4.domain;

import java.util.Collections;
import java.util.List;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public List<BowlingGame> bowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }

    public boolean isAllFinished() {
        return bowlingGames.stream()
                .allMatch(BowlingGame::hasFinishedGame);
    }
}
