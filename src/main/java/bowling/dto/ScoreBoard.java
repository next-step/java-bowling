package bowling.dto;

import bowling.domain.Players;

import java.util.List;

public class ScoreBoard {

    private int totalNumberOfFrame;

    private List<PlayResult> playResults;

    public ScoreBoard(Players players, int totalNumberOfFrame) {
        this.playResults = players.playResults();
        this.totalNumberOfFrame = totalNumberOfFrame;
    }

    public List<PlayResult> playResults() {
        return playResults;
    }

    public int totalNumberOfFrame() {
        return totalNumberOfFrame;
    }
}
