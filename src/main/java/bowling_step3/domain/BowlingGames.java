package bowling_step3.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {

    private List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames of(List<Player> players) {
        return new BowlingGames(setBowlingGamesOfPlayer(players));
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }

    private static List<BowlingGame> setBowlingGamesOfPlayer(List<Player> players) {
        return players
                .stream()
                .map(BowlingGame::new)
                .collect(Collectors.toList());
    }
}
