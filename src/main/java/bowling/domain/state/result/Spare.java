package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import bowling.domain.score.ProgressScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Spare extends End {

    private final Pins pins;

    private Spare(Pins pins) {
        this.pins = pins;
    }

    public static Spare of(Pins pins) {
        return new Spare(pins);
    }

    @Override
    public boolean isAllHit() {
        return true;
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.singletonList(pins.getCountHitPins());
    }

    @Override
    public Score score() {
        return ProgressScore.ofSpare();
    }

    @Override
    public Score addBonusScore(Score score) {
        Score resultScore = score.add(pins.score());

        if (resultScore.isCompute()) {
            return resultScore;
        }

        return resultScore.add(pins.spareScore());
    }

}
