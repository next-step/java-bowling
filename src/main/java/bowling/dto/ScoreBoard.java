package bowling.dto;

import bowling.domain.Player;

public class ScoreBoard {

    private int totalNumberOfFrame;

    private PlayResult playResult;

    public ScoreBoard(Player player, int totalNumberOfFrame) {
        this.playResult = player.playResult();
        this.totalNumberOfFrame = totalNumberOfFrame;
    }

    public PlayResult playResult() {
        return playResult;
    }

    public int totalNumberOfFrame() {
        return totalNumberOfFrame;
    }
}
