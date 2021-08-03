package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.InProgressScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Spare extends EndState {
    private final DownedPins downedPins;

    private Spare(DownedPins downedPins) {
        this.downedPins = downedPins;
    }

    public static Spare from(DownedPins downedPins) {
        return new Spare(downedPins);
    }

    @Override
    public Score score() {
        return InProgressScore.ofSpare();
    }

    @Override
    protected Score addBonusScore(Score score) {
        Score addScore = score.add(downedPins.score());

        if (addScore.isCalculable()) {
            return addScore;
        }

        return addScore.add(downedPins.padScore());
    }

    @Override
    protected boolean isClean() {
        return true;
    }

    @Override
    public List<Integer> getDownedPins() {
        return Collections.singletonList(downedPins.getNumOfDownedPins());
    }
}
