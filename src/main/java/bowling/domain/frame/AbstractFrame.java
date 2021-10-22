package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    public static final int FIRST_ROUND = 1;
    public static final int FINAL_ROUND = 10;

    private final int round;

    public AbstractFrame(int round) {
        this.round = round;
    }

    @Override
    public int round() {
        return round;
    }

    @Override
    public Frame lastFrame() {
        Frame resultFrame = this;
        return calculateLastFrame(resultFrame);
    }

    private Frame calculateLastFrame(Frame resultFrame) {
        while (resultFrame.round() < FINAL_ROUND && Objects.nonNull(resultFrame.nextFrame())) {
            resultFrame = resultFrame.nextFrame();
        }
        return resultFrame;
    }

    public abstract Frame nextFrame();

    public abstract Frame bowling(Pin pin);

    public abstract FrameResult createFrameResult();

    public abstract Score calculateAdditionalScore(Score score);

    public abstract boolean isFinished();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractFrame that = (AbstractFrame) o;
        return round == that.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }

}
