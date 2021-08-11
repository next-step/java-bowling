package bowling.domain;

import bowling.domain.player.Bowler;

public class BowlingGame {

    private final Bowler bowler;

    private BowlingGame(Bowler bowler) {
        this.bowler = bowler;
    }

    public static BowlingGame start(Bowler bowler) {
        return new BowlingGame(bowler);
    }

    public void play(int pins) {
        bowler.playBowling(pins);
    }

    public boolean isNotFinish() {
        return !bowler.isBowlingFinish();
    }

}
