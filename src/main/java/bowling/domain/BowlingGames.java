package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<String> players) {
        bowlingGames = players.stream()
                .map(BowlingGame::new)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                Collections::unmodifiableList));
    }

    public boolean isEnd() {
        return bowlingGames.stream().allMatch(BowlingGame::isEnd);
    }

    public List<BowlingGame> games() {
        return bowlingGames;
    }
}
