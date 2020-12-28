package bowling_step3.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGames {

    private List<BowlingGame> bowlingGames;

    private BowlingGames(List<BowlingGame> bowlingGames) {
        this.bowlingGames = bowlingGames;
    }

    public static BowlingGames of(Players players) {
        return new BowlingGames(setBowlingGamesOfPlayer(players));
    }

    public List<BowlingGame> getBowlingGames() {
        return bowlingGames;
    }

    public int size() {
        return bowlingGames.size();
    }

    public Player player(int index) {
        return bowlingGames.get(index).getPlayer();
    }

    public BowlingGame frame(int index) {
        return bowlingGames.get(index);
    }

    public Scores sumScore(int index) {
        return bowlingGames.get(index).sumScore();
    }

    private static List<BowlingGame> setBowlingGamesOfPlayer(Players players) {
        List<BowlingGame> bowlingGames = new ArrayList<>();
        players.getPlayers()
                .forEach(player -> bowlingGames.add(new BowlingGame(player)));
        return bowlingGames;
    }
}
