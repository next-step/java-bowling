package bowling.domain.dto;

import bowling.domain.player.Bowler;

import java.util.List;

public class BowlerData {
    private final String name;
    private final int currentFrameNumber; // 1 base
    private final List<StateData> states;
    private final List<ScoreData> scores;

    private BowlerData(Bowler bowler) {
        this.name = bowler.getName();
        this.states = bowler.getStates();
        this.scores = bowler.getScores();
        this.currentFrameNumber = states.size();
    }

    public static BowlerData of(Bowler bowler) {
        return new BowlerData(bowler);
    }

    public String getBowlerName() {
        return name;
    }

    public int getCurrentFrameNumber() {
        return currentFrameNumber;
    }

    public List<StateData> getStates() {
        return states;
    }

    public List<ScoreData> getScores() {
        return scores;
    }

}
