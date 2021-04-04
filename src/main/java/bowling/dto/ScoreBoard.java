package bowling.dto;

import bowling.domain.Player;

public class ScoreBoard {

    private int totalFrameNumber;

    private PlayResult playResult;

    public ScoreBoard(Player player, int totalFrameNumber) {
        this.playResult = player.playResult();
        this.totalFrameNumber = totalFrameNumber;
    }

    public PlayResult playResult() {
        return playResult;
    }

    public int totalFrameNumber() {
        return totalFrameNumber;
    }
}
