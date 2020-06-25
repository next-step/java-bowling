package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private Player player;
    private List<BowlingGameResult> bowlingGameResults;

    public BowlingGame(Player player, List<BowlingGameResult> bowlingGameResults) {
        this.player = player;
        this.bowlingGameResults = bowlingGameResults;
    }

    public static BowlingGame start(String playerName) {
        return new BowlingGame(Player.createByName(playerName), new ArrayList<>());
    }

    public int checkWhereFrame() {
        return this.bowlingGameResults.size();
    }
}
