package bowling.dto;

import bowling.domain.player.Bowler;

public class BowlingPlayResultData {
    private final String name;

    private BowlingPlayResultData(Bowler bowler) {
        this.name = bowler.getName();
    }

    public static BowlingPlayResultData of(Bowler bowler) {
        return new BowlingPlayResultData(bowler);
    }

    public String getName() {
        return name;
    }

}
