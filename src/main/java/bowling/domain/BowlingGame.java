package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private Player player;
    private List<BowlingGameResult> bowlingGameResults;

    public BowlingGame(Player player, List<BowlingGameResult> bowlingGameResults) {
        this.player = player;
        this.bowlingGameResults = new ArrayList<>(bowlingGameResults);
    }

    public static BowlingGame start(String playerName) {
        return new BowlingGame(Player.createByName(playerName), new ArrayList<>());
    }

    public int checkWhereFrame() {
        return this.bowlingGameResults.size();
    }

    public List<BowlingGameResult> bowlFirst(int numberOfHitPin) {
        Frame frame = player.bowlFirstRefactor(numberOfHitPin);
        BowlingGameResult bowlingGameResult = new BowlingGameResult(frame.calculateCurrentResults());

        List<BowlingGameResult> temp = new ArrayList<>(bowlingGameResults);
        temp.add(bowlingGameResult);

        return temp;
    }
}
