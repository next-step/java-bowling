package bowling.domain.dto;

import bowling.domain.player.Bowler;

import java.util.List;

public class BowlingGameResult {
    private final String name;
    private final List<StateData> states;
    private final List<ScoreData> scores;

    private BowlingGameResult(Bowler bowler) {
        this.name = bowler.getName();
        this.states = bowler.getStates();
        this.scores = bowler.getScores();
    }

    public static BowlingGameResult of(Bowler bowler) {
        return new BowlingGameResult(bowler);
    }

    public String getPlayerName() {
        return name;
    }

    public List<StateData> getStates() {
        return states;
    }

    public int getFrameCount() {
        return states.size();
    }

    public List<ScoreData> getScores() {
        return scores;
    }

}
