package bowling.io;

import bowling.model.Player;
import bowling.model.frame.Results;

public class Board {

    private Player player;
    private Results bowlingResults;

    public Board(Player player, Results bowlingResults) {
        this.player = player;
        this.bowlingResults = bowlingResults;
    }

    Player getPlayer() {
        return player;
    }

    Results getBowlingResults() {
        return bowlingResults;
    }
}
