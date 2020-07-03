package bowling.step4.domain.process;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;

    public BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames of(List<String> playerNames) {
        return playerNames.stream()
                          .map(BowlingGame::of)
                          .collect(collectingAndThen(Collectors.toList(), BowlingGames::new));
    }

    public boolean isGamesOver() {
        return bowlingGames.stream()
                           .allMatch(bowlingGame -> bowlingGame.isGameOver() == true);
    }

    public int size(){
        return bowlingGames.size();
    }

    public BowlingGame getOneGame(int order) {
        return bowlingGames.get(order);
    }

    public List<BowlingGame> getBowlingGames() {
        return Collections.unmodifiableList(bowlingGames);
    }
}
