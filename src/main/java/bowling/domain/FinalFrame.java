package bowling.domain;

import java.util.Objects;

/**
 * Created : 2020-12-16 오전 9:09
 * Developer : Seo
 */
public class FinalFrame extends Frame {
    private final Score score;

    public FinalFrame(int downPins) {
        super(Frame.FINAL_FRAME_NO + 1);
        this.score = new Score(downPins);
    }

    @Override
    public Frame bowl(int downPins) {
        this.score.setSecond(downPins);

        if (isStrike(downPins) || isSpare(downPins)) {
            this.score.setTenFrameBonus(Bowling.stroke(Frames.ALL_PINS));
        }

        return this;
    }

    private boolean isSpare(int downPins) {
        return downPins != Symbol.STRIKE.getScore() && score.get() == Symbol.SPARE.getScore();
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
