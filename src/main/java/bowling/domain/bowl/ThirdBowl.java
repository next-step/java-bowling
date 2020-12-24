package bowling.domain.bowl;

import bowling.domain.Symbol;
import bowling.domain.score.Pins;
import bowling.domain.state.*;

/**
 * Created : 2020-12-22 오후 2:29
 * Developer : Seo
 */
public class ThirdBowl implements Bowl {
    private final State state;

    public ThirdBowl(State state) {
        this.state = state;
    }

    @Override
    public State stroke(Pins pins) {
        // 독립시행이냐 스페어냐
        // 2구가 스트라이크 or 스페어면 리셋

        // 리셋 후 스트라이크
        if (isResetPinsAndStrike(pins)) {
            return new Strike();
        }
        // 리셋 후 거터
        if (isResetPinsAndGutter(pins)) {
            return new Gutter(pins);
        }
        // 스페어
        if (isSpare(pins)) {
            return new Spare(this.state.getScore().getSecond(), pins);
        }
        return new Miss(pins);
    }

    private boolean isResetPinsAndStrike(Pins pins) {
        return (this.state instanceof Strike || this.state instanceof Spare)
                && (pins.get() == Symbol.STRIKE.getPins().get());
    }

    private boolean isResetPinsAndGutter(Pins pins) {
        return (this.state instanceof Strike || this.state instanceof Spare)
                && (pins.get() == Symbol.GUTTER.getPins().get());
    }

    private boolean isSpare(Pins pins) {
        return (this.state instanceof Miss || this.state instanceof Gutter)
                && (this.state.getScore().getSecond().get() + pins.get() == Symbol.SPARE.getPins().get());
    }
}
