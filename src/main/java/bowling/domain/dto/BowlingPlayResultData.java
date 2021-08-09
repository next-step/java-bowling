package bowling.domain.dto;

import bowling.domain.player.Bowler;

import java.util.List;

public class BowlingPlayResultData {
    private final String name;
    private final List<StateDatas> states;

    private BowlingPlayResultData(Bowler bowler) {
        this.name = bowler.getName();
        this.states = bowler.getStates();
    }

    public static BowlingPlayResultData of(Bowler bowler) {
        return new BowlingPlayResultData(bowler);
    }

    public String getPlayerName() {
        return name;
    }

    public List<StateDatas> getStates() {
        return states;
    }

    public int getFrameCount() {
        return states.size();
    }


}
