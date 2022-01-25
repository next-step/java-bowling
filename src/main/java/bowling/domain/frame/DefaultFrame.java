package bowling.domain.frame;

import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Pitch;
import java.util.List;
public abstract class DefaultFrame implements Frame {
    public static final int thirdScoreKey = 3;
    public static final int firstScoreKey = 1;
    public static final int secondScoreKey = 2;
    protected Score score;
    protected int frameNumber;
    protected List<Pitch> pitchs;
    protected DefaultFrame(int frameNumber) {
        this.frameNumber = frameNumber;
    }
    public KnockedPins getFirstScore() {

        return firstKnockedPins;
    }
    public KnockedPins getSecondScore() {
        return secondKnockedPins;
    }

    public boolean isEmpty() {
        return firstKnockedPins == null;
    }

    public boolean isStrike() {
        return firstKnockedPins.equals(new KnockedPins(10));
    }
}
