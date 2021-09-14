package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    public static final int START_INDEX = 0;

    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<String> players) {
        bowlingGames = Collections.unmodifiableList(
                players.stream()
                        .map(BowlingGame::new)
                        .collect(Collectors.toList())
        );
    }

    public boolean isEnd() {
        return bowlingGames.stream().allMatch(BowlingGame::isEnd);
    }

    public List<BowlingGame> games() {
        return bowlingGames;
    }
}
