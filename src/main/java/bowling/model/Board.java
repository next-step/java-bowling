package bowling.model;

import bowling.model.frame.Results;

public class Board {

    private Player player;
    private Results bowlingResults;

    Board(Player player, Results bowlingResults) {
        this.player = player;
        this.bowlingResults = bowlingResults;
    }

    public Player getPlayer() {
        return player;
    }

    public Results getBowlingResults() {
        return bowlingResults;
    }
}
