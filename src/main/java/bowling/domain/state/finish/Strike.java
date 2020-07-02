package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class Strike extends Finished {

    private static final Strike INSTANCE = new Strike();

    private Strike() {
    }

    public static Strike getInstance() {
        return INSTANCE;
    }

    @Override
    public Pins getFirstPins() {
        return Pins.of(PinCount.MAX_COUNT);
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public Score calculateScoreForExtraBonusCount(final Score beforeScore) {
        return beforeScore.sum(PinCount.of(PinCount.MAX_COUNT));
    }
}
