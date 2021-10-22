package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

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

    public abstract Frame nextFrame();

    public abstract Frame bowling(Pin pin);

    public abstract FrameResult createFrameResult();

    public abstract Score calculateAdditionalScore(Score score);

    public abstract boolean isFinished();

}
