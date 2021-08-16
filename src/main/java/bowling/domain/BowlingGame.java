package bowling.domain;

import bowling.domain.dto.BowlerData;
import bowling.domain.player.Bowler;
import bowling.domain.player.Bowlers;

import java.util.List;

public class BowlingGame {
    private final Bowlers bowlers;
    private Bowler bowler;

    public BowlingGame(List<String> names) {
        bowlers = Bowlers.of(names);
        bowler = bowlers.firstBowler();
    }

    public static BowlingGame start(List<String> names) {
        return new BowlingGame(names);
    }

    public BowlerData currentBowler() {
        return BowlerData.of(bowler);
    }

    public void play(int pins) {
        bowler.playBowling(pins);
        changeBowler();
    }

    private void changeBowler() {
        if (bowler.isBowlerChange()) {
            bowler = bowlers.changeBowler(bowler);
        }
    }

    public List<BowlerData> bowlerData() {
        return bowlers.bowlerData();
    }

    public boolean isNotFinish() {
        return !bowlers.isAllBowlerFinish();
    }

}
