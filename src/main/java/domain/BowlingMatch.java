package domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingMatch {

    private List<BowlingGame> bowlingMatch;

    private BowlingMatch(List<BowlingGame> bowlingMatch) {
        this.bowlingMatch = bowlingMatch;
    }

    public static BowlingMatch start(List<Player> players) {
        return new BowlingMatch(setUpMatch(players));
    }

    private static List<BowlingGame> setUpMatch(List<Player> players) {
        return players.stream()
                .map(BowlingGame::from)
                .collect(Collectors.toList());
    }
}
