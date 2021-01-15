package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {

    private List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames){
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames from(Players players){
        return new BowlingGames(initBowlingGame(players));
    }

    private static List<BowlingGame> initBowlingGame(Players players) {
        return players.getPlayers()
                .stream()
                .map(player -> BowlingGame.of(player, Frames.of()))
                .collect(Collectors.toList());
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }
}
