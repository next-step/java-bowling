package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;

public class Progress extends State {
    private final Pins pins;

    private Progress(Pins pins) {
        this.pins = pins;
        resultState = ResultState.NONE;
        progressState = ProgressState.NONE;
    }

    public static Progress of(Pins pins) {
        return new Progress(pins);
    }

    @Override
    public State hitPins(Pins pins) {
        Pins hitPinsTotal = this.pins.add(pins);

        if (hitPinsTotal.isAllHit()) {
            return Spare.of(this.pins);
        }

        return Miss.of(this.pins, pins);
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.singletonList(pins.getCountHitPins());
    }

    @Override
    public Score addBonusScore(Score score) {
        return score.add(pins.score());
    }
}
