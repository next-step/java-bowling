package bowling.domain;

import java.util.Objects;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame extends Frame {

    public FinalFrame(Pins downPins) {
        super(Frames.ALL_FRAMES, new Score(downPins));
    }

    @Override
    public Frame bowl(Pins downPins) {
        super.score.setSecond(downPins);

        if (isFirstStrike() || isStrike(downPins) || isSpare()) {
            bonusStroke();
        }

        return this;
    }

    private boolean isFirstStrike() {
        return score.getFirst() == Symbol.STRIKE.getScore();
    }

    private boolean isSpare() {
        return score.get() == Symbol.SPARE.getScore();
    }

    private void bonusStroke() {
        super.score.setTenFrameBonus(Bowling.stroke(Frame.ALL_PINS));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FinalFrame that = (FinalFrame) o;
        return score.equals(that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), score);
    }
}
