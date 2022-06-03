package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;

public class BeforeProgress extends ProgressState {
    private static final String NONE = "";

    @Override
    public FrameState bowl(Pins hitPins) {
        if (hitPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(hitPins);
    }

    @Override
    public String symbol() {
        return NONE;
    }

    @Override
    public Score score() {
        return Score.init();
    }

    @Override
    public Score calculateAdditionalScore(Score previousScore) {
        return Score.init();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}